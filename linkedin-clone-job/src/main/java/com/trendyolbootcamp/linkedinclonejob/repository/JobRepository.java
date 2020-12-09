package com.trendyolbootcamp.linkedinclonejob.repository;

import com.couchbase.client.core.error.DocumentNotFoundException;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.json.JsonArray;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.query.QueryResult;
import com.trendyolbootcamp.linkedinclonejob.domain.Job;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static com.couchbase.client.java.query.QueryOptions.queryOptions;

@Repository
public class JobRepository {
    private final Cluster couchbaseCluster;
    private final Collection jobCollection;

    public JobRepository(Cluster couchbaseCluster, Collection jobCollection) {
        this.couchbaseCluster = couchbaseCluster;
        this.jobCollection = jobCollection;
    }

    public Job findById(String id){
        GetResult getResult = jobCollection.get(id);
        Job job = getResult.contentAs(Job.class);
        job.setId(id);
        return job;
    }

    public Optional<Job> findByIdOptional(String id) {
        try {
            GetResult getResult = jobCollection.get(id);
            Job job = getResult.contentAs(Job.class);
            return Optional.of(job);
        } catch (DocumentNotFoundException exception) {
            return Optional.empty();
        }
    }

    public void insert(Job job) {
        jobCollection.insert(job.getId(), job);
    }

    public void update(Job job) {
        jobCollection.replace(job.getId(),job);
    }

    public List<Job> findAll() {
        String statement = "Select id,title,city,sector,company,numberOfHires,requirements,applicants from job";
        QueryResult query = couchbaseCluster.query(statement);
        return query.rowsAs(Job.class);
    }

    public void delete(Job job) {
        jobCollection.remove(job.getId());
    }

    public List<Job> filter(String city) {
        String statement = "select id,title,city,sector,company,numberOfHires,requirements,applicants from job where lower(city) like $city";
        QueryResult query = couchbaseCluster.query(
                statement, queryOptions().parameters(JsonObject.create().put("city",'%'+city+'%')));

        return query.rowsAs(Job.class);
    }

    public List<Job> filterJobs(Optional<String> sector, Optional<String> city, Optional<String> company) {
        String statement = "select id,title,city,sector,company,numberOfHires,requirements,applicants from job ";
        Locale.setDefault(new Locale("tr","TR"));
        if(sector.isPresent() || city.isPresent() || company.isPresent() ){
            statement += "where ";
            boolean addedWhereBefore = false;
            if(city.isPresent()){
                statement += "lower(city) like '%" + city.get().toLowerCase() + "%' ";
                addedWhereBefore = true;
            }
            if(sector.isPresent()){
                if (addedWhereBefore) statement += "and ";
                statement += "lower(sector) like '%" + sector.get().toLowerCase() + "%' ";
            }
            if(company.isPresent()){
                if (addedWhereBefore) statement += "and ";
                statement += "lower(company) like '%" + company.get().toLowerCase() + "%' ";
            }
        }
        QueryResult queryResult = couchbaseCluster.query(statement);
        return queryResult.rowsAs(Job.class);
    }

}
