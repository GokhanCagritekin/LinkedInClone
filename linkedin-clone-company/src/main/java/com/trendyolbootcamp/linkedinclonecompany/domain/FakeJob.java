package com.trendyolbootcamp.linkedinclonecompany.domain;

import java.util.List;
import java.util.UUID;

public class FakeJob {

    private String id;
    private String title;
    private String sector;
    private String city;
    private String company;
    private int numberOfHires;
    private List requirements;
    private List applicants;

    public FakeJob(){
        this.id = "123";
        this.title = "FakeDataJob.title";
        this.sector = "FakeDataJob.sector";
        this.city =  "FakeDataJob.city";
        this.company = "FakeDataJob.company";
        this.numberOfHires = 10;
        this.requirements = null;
        this.applicants = null;
    }

}
