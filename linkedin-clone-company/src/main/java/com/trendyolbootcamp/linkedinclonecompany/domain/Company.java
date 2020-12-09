package com.trendyolbootcamp.linkedinclonecompany.domain;

import java.util.List;
import java.util.UUID;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Company {


    private String id;
    private String name;
    private String sector;
    private String city;
    private String companyExplanation;
    private int numberOfTotalEmployee;
    private List jobPostings;

    public Company() {
    }

    /*
    public Company(){
        this.id = UUID.randomUUID().toString();
        this.name = "FakeCompany.name";
        this.sector = "FakeCompany.sector";
        this.city =  "FakeCompany.city";
        this.companyExplanation = "FakeCompany.companyExplanation";
        this.numberOfTotalEmployee = 1000;
        this.jobPostings = null;
    }*/

    public Company(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public Company(String name, String sector, String city, String companyExplanation,  int numberOfTotalEmployee, List jobPostings) {
        this.id = UUID.randomUUID().toString();
        setCompany(name, sector, city, companyExplanation, numberOfTotalEmployee,jobPostings);
    }

    public void setCompany(String name, String sector, String city, String companyExplanation,  int numberOfTotalEmployee, List jobPostings){
       this.setName(name);
       this.setSector(sector);
       this.setCity(city);
       this.setCompanyExplanation(companyExplanation);
       this.setNumberOfTotalEmployee(numberOfTotalEmployee);
       this.setJobPostings(jobPostings);
    }



    public String getId() { return this.id; }

}
