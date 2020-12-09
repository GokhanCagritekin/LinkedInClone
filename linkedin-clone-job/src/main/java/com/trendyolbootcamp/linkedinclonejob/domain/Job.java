package com.trendyolbootcamp.linkedinclonejob.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Job {
    private String id;
    private String title;
    private String sector;
    private String city;
    private String company;
    private int numberOfHires;
    private List requirements;
    private List applicants;


    public Job(){
        this.id = UUID.randomUUID().toString();
        this.title = FakeDataJob.title;
        this.sector = FakeDataJob.sector;
        this.city =  FakeDataJob.city;
        this.company = FakeDataJob.company;
        this.numberOfHires = FakeDataJob.numberOfHires;
        this.requirements = FakeDataJob.requirements;
        this.applicants =  FakeDataJob.applicants;
    }


    public void updateJobTitle(String title) {
        this.title = title;
    }

    public void updateJobSector(String sector) {
        this.sector = sector;
    }

    public void updateJobCity(String city) {
        this.city = city;
    }

    public void updateJobCompany(String company) {
        this.company = company;
    }

    public void updateJobNumberOfHires(int numberOfHires) {
        this.numberOfHires = numberOfHires;
    }

    public void updateJobRequirements(ArrayList<String> requirements) {
        this.requirements = requirements;
    }

    public void updateApplicants(ArrayList<String> applicants) {
        this.applicants = applicants;
    }

    public void updateJob(Job job) {
        if(this.title != "string"){
            this.title = job.title;
        }
        this.sector = job.sector;
        this.company = job.company;
        this.city = job.city;
        this.numberOfHires = job.numberOfHires;
        this.applicants = job.applicants;
        this.requirements = job.requirements;
    }

}
