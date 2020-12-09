package com.trendyolbootcamp.linkedinclonecompany.repository;

import com.couchbase.client.core.error.DocumentNotFoundException;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.query.QueryResult;
import com.trendyolbootcamp.linkedinclonecompany.domain.Company;
import org.springframework.stereotype.Repository;

import java.text.Normalizer;
import java.util.*;

@Repository
public class CompanyRepository {

    private final Cluster couchbaseCluster;
    private final Collection companyCollection;

    public CompanyRepository(Cluster couchbaseCluster, Collection companyCollection) {
        this.couchbaseCluster = couchbaseCluster;
        this.companyCollection = companyCollection;
    }

    // get company from db with id
    public Company findById(String id){

        GetResult getResult = companyCollection.get(id);
        Company company = getResult.contentAs(Company.class);

        return company;
    }

    // get company from db with id optional
    public Optional<Company> findByIdOptional(String id) {

        try {
            GetResult getResult = companyCollection.get(id);
            Company company = getResult.contentAs(Company.class);
            return Optional.of(company);

        } catch (DocumentNotFoundException exception) {
            return Optional.empty();
        }
    }

    // get all companies from db
    public List<Company> findAll () {
        String statement = "Select id, name, sector, city, companyExplanation, numberOfTotalEmployee, jobPostings from company";
        QueryResult query = couchbaseCluster.query(statement);
        return query.rowsAs(Company.class);
    }

    // insert new company to db
    public Company insert(Company company) {
        company.setId(UUID.randomUUID().toString());
        companyCollection.insert(company.getId(), company);
        return company;
    }

    // update a company in db
    public void update(Company company) {
        companyCollection.replace(company.getId(), company);
    }

    // delete a company with id from db
    public void delete(String companyId) { companyCollection.remove(companyId); }


    // filter a company with city
    public List<Company> filterByCity(Optional<String> city){

        String statement = "Select * from company";
        if(city.isPresent()){
            statement += "where city == " + city;
        }
        QueryResult queryResult = couchbaseCluster.query(statement);
        return queryResult.rowsAs(Company.class);
    }

    public List<Company> filterCompanies(Optional<String> sector, Optional<String> city) {

        Locale.setDefault(new Locale("tr","TR"));

        String statement = "Select id,city,companyExplanation,jobPostings,name,numberOfTotalEmployee,sector from company ";
        if(sector.isPresent() || city.isPresent()){
            statement += "where ";
            boolean addedWhereBefore = false;

            if(city.isPresent()){
                statement += "lower(city) like '%" + city.get().toLowerCase()+ "%' ";
                addedWhereBefore = true;
            }
            if(sector.isPresent()){
                if (addedWhereBefore) statement += "and ";
                statement += "lower(sector) like '%" + sector.get().toLowerCase() + "%' ";
            }
        }
        QueryResult queryResult = couchbaseCluster.query(statement);
        return queryResult.rowsAs(Company.class);
    }


    // ilan yayınla
    // verilen ilana başvuran tüm adayları görüntüle
    // verilen ilana başvuran tüm adaylar arasından filterlama yap(şehir ve okula göre)


}
