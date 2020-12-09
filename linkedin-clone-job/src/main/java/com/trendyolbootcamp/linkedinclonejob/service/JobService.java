package com.trendyolbootcamp.linkedinclonejob.service;

import com.trendyolbootcamp.linkedinclonejob.domain.Job;
import com.trendyolbootcamp.linkedinclonejob.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public void placeJob(Job job) {
        jobRepository.insert(job);
    }

    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    public Job getJobById(String id) {
        Job job = jobRepository.findById(id);
        return job;
    }

    public Job updateJob(String id,Job job) {
        Job orjJob = jobRepository.findById(id);
        orjJob.updateJob(job);
        jobRepository.update(orjJob);
        return orjJob;
    }

    public void updateJob(String id, Optional<String> title, Optional<String> sector,
                          Optional<String> city, Optional<String> company, Optional<Integer> numberOfHires,
                          Optional<List> requirements, Optional<List> applicants) {
        Optional<Job> optionalJob = jobRepository.findByIdOptional(id);

        if (!optionalJob.isPresent()){
            // TODO Throw a reasonable exception
            return;
        }

        Job job = optionalJob.get();
        if (title.isPresent()) job.setTitle(title.get());
        if (sector.isPresent()) job.setSector(sector.get()) ;
        if (city.isPresent()) job.setCity(city.get());
        if (company.isPresent()) job.setCompany(company.get());
        if (numberOfHires.isPresent()) job.setNumberOfHires(numberOfHires.get());
        if (requirements.isPresent()) job.setRequirements(requirements.get());
        if (applicants.isPresent()) job.setApplicants(applicants.get());

        jobRepository.update(job);
    }

    public void deleteJob(String id) {
        Job job = jobRepository.findById(id);
        jobRepository.delete(job);
    }

    public List<Job> filterJob(String city){
        return jobRepository.filter(city);
    }

    public List getJobApplicants(String id) {
        Job job = jobRepository.findById(id);
        return job.getApplicants();
    }

    public void addJobApplicant(String id, String candidateId) {
        Job job = jobRepository.findById(id);
        job.getApplicants().add(candidateId);
        jobRepository.update(job);
    }

    public List<Job> filterJobs(Optional<String> sector, Optional<String> city, Optional<String> company) {
        return jobRepository.filterJobs(sector, city, company);
    }

}
