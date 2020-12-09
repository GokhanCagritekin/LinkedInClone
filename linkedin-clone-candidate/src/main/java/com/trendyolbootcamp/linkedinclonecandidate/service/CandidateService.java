package com.trendyolbootcamp.linkedinclonecandidate.service;

import com.couchbase.client.core.error.DocumentNotFoundException;
import com.netflix.discovery.converters.Auto;
import com.trendyolbootcamp.linkedinclonecandidate.domain.Candidate;
import com.trendyolbootcamp.linkedinclonecandidate.repository.CandidateRepository;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.omg.CORBA.TIMEOUT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;



@Service
public class CandidateService {

    private static final int TIMEOUT = 1000;
    private final CandidateRepository candidateRepository;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public void placeCandidate(Candidate candidate) {
        candidateRepository.insert(candidate);
    }

    public void deleteCandidate(String candidateID) {
        candidateRepository.delete(candidateID);
    }

    public List<Candidate> findAll() {
        return candidateRepository.findAll();
    }

    public void updateCandidate(String candidateID, Optional<String> name, Optional<String> surname, Optional<String> email, Optional<String> university, Optional<String> city, Optional<List> projects, Optional<List> favoriteJobs, Optional<List> appliedJobs) {
        Optional<Candidate> optionalCandidate = candidateRepository.findByIdOptional(candidateID);

        if (!optionalCandidate.isPresent()){
            // TODO Throw a reasonable exception
            return;
        }

        Candidate candidate = optionalCandidate.get();
        if (name.isPresent()) candidate.setName(name.get());
        if (surname.isPresent()) candidate.setSurname(surname.get());
        if (email.isPresent()) candidate.setEMail(email.get());
        if (university.isPresent()) candidate.setUniversity(university.get());
        if (city.isPresent()) candidate.setCity(city.get());
        if (projects.isPresent()) candidate.setProjects(projects.get());
        if (favoriteJobs.isPresent()) candidate.setFavoriteJobs(favoriteJobs.get());
        if (appliedJobs.isPresent()) candidate.setAppliedJobs(appliedJobs.get());

        candidateRepository.update(candidate);
    }

    public void addToAppliedJobs(String candidateID, String jobID) {
        Optional<Candidate> optionalCandidate = candidateRepository.findByIdOptional(candidateID);

        if (!optionalCandidate.isPresent()){
            throw new IllegalArgumentException("candidateID could not find in the database!");
        }

        Candidate candidate = optionalCandidate.get();
        candidate.getAppliedJobs().add(jobID);
        candidateRepository.update(candidate);

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(TIMEOUT);
        requestFactory.setReadTimeout(TIMEOUT);

        restTemplate.setRequestFactory(requestFactory);

        // Job Servisine İstek Gönder
        String requestURL = "http://localhost:8003/job/"+ jobID + "/" + candidateID;
        HttpEntity<String> request = new HttpEntity<>(new String("bar"));
        try {
            restTemplate.exchange(requestURL, HttpMethod.PATCH, request, Object.class);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Candidate> filterCandidates(Optional<String> university, Optional<String> city) {
        return candidateRepository.filterCandidates(university, city);
    }

    public void addToFavoriteJobs(String candidateID, String jobID) {
        Optional<Candidate> optionalCandidate = candidateRepository.findByIdOptional(candidateID);

        if (!optionalCandidate.isPresent()){
            // TODO Throw a reasonable exception
            return;
        }

        Candidate candidate = optionalCandidate.get();
        candidate.getFavoriteJobs().add(jobID);
        candidateRepository.update(candidate);

        // Job Servisine İstek Gönder
        /*
        JSONObject jsonObject = new JSONObject();
        String requestURL = "http://localhost:8003/job/"+ jobID + "/" + candidateID;
        HttpEntity<RestTemplate> request = new HttpEntity<>(new RestTemplate());
        try {
            jsonObject.put("message", restTemplate.exchange(requestURL, HttpMethod.POST, request, Object.class).getBody());
        } catch (JSONException e) {
            e.printStackTrace();
        }
         */
    }

    public void deleteAJobFromFavoriteJobs(String candidateID, String jobID) {
        Optional<Candidate> optionalCandidate = candidateRepository.findByIdOptional(candidateID);

        if (!optionalCandidate.isPresent()){
            throw new IllegalArgumentException("candidateID could not find in the database!");
        }

        Candidate candidate = optionalCandidate.get();
        boolean result = candidate.getFavoriteJobs().remove(jobID);
        if (!result) throw new IllegalArgumentException("JobID that you want to delete is not in the favorite jobs list!");

        candidateRepository.update(candidate);
    }

    public Candidate getCandidateByID(String candidateID) {
        Optional<Candidate> optionalCandidate = candidateRepository.findByIdOptional(candidateID);
        if (!optionalCandidate.isPresent()){
            //TODO 404 Hatası nasıl dönülür?
            throw new IllegalArgumentException("candidateID could not find in the database!");
        }

        Candidate candidate = optionalCandidate.get();

        return candidate;
    }


}
