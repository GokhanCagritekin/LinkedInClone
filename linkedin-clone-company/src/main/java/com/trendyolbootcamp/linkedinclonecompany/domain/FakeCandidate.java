package com.trendyolbootcamp.linkedinclonecompany.domain;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FakeCandidate {

    private String id;
    private String name;
    private String surname;
    private String eMail;
    private String university;
    private String city;
    private List resumes;
    private List favoriteJobs;
    private List appliedJobs;

    public FakeCandidate(){
        this.id = UUID.randomUUID().toString();
        this.name = "FakeDataJob.title";
        this.surname = "FakeDataJob.sector";
        this.eMail =  "FakeDataJob.city";
        this.university = "FakeDataJob.company";
        this.city = "Istanbul";
        this.resumes = null;
        this.favoriteJobs = null;
        this.appliedJobs = null;
    }

}
