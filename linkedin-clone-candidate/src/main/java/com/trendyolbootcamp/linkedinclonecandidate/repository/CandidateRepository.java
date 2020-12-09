package com.trendyolbootcamp.linkedinclonecandidate.repository;

import com.couchbase.client.core.error.DocumentNotFoundException;
import com.couchbase.client.core.msg.query.QueryResponse;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.query.QueryResult;
import com.trendyolbootcamp.linkedinclonecandidate.domain.Candidate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CandidateRepository {

    private final Cluster couchbaseCluster;
    private final Collection candidateCollection;

    public CandidateRepository(Cluster couchbaseCluster, Collection candidateCollection) {
        this.couchbaseCluster = couchbaseCluster;
        this.candidateCollection = candidateCollection;
    }

    public void insert(Candidate candidate) {
        candidate.setId(UUID.randomUUID().toString());
        candidateCollection.insert(candidate.getId(), candidate);
    }

    public void update(Candidate candidate) {
        candidateCollection.replace(candidate.getId(), candidate);
    }

    public void delete(String candidateID) {
        candidateCollection.remove(candidateID);
    }

    public Candidate findById(String candidateID) {
        GetResult getResult = candidateCollection.get(candidateID);
        Candidate candidate = getResult.contentAs(Candidate.class);
        return candidate;
    }

    public Optional<Candidate> findByIdOptional(String candidateID) {
        try {
            GetResult getResult = candidateCollection.get(candidateID);
            Candidate candidate = getResult.contentAs(Candidate.class);
            return Optional.of(candidate);
        } catch (DocumentNotFoundException exception) {
            return Optional.empty();
        }
    }

    public List<Candidate> findAll() {
        String statement = "Select id, name, surname, email, university, city, projects, favoriteJobs, appliedJobs from candidate";
        QueryResult queryResult = couchbaseCluster.query(statement);
        return queryResult.rowsAs(Candidate.class);
    }

    public List<Candidate> filterCandidates(Optional<String> university, Optional<String> city) {
        String statement = "Select id, name, surname, email, university, city, projects, favoriteJobs, appliedJobs from candidate ";
        Locale.setDefault(new Locale("tr","TR"));
        if(university.isPresent() || city.isPresent()){
            statement += "where ";
            boolean addedWhereBefore = false;
            // TODO Case sensitive arama yapmalı!
            if(city.isPresent()){
                statement += "lower(city) like '%" + city.get().toLowerCase() + "%' ";
                addedWhereBefore = true;
            }
            if(university.isPresent()){
                if (addedWhereBefore) statement += "and ";
                statement += "lower(university) like '%" + university.get().toLowerCase() + "%' ";
            }
        }
        QueryResult queryResult = couchbaseCluster.query(statement);
        return queryResult.rowsAs(Candidate.class);
    }

}
