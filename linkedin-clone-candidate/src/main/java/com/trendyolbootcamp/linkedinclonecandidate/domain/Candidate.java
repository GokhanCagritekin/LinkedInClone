package com.trendyolbootcamp.linkedinclonecandidate.domain;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Candidate {
    private String id;
    private String name;
    private String surname;
    private String eMail;
    private String university;
    private String city;
    private List projects;
    private List favoriteJobs;
    private List appliedJobs;

    public Candidate() {
        this.id = UUID.randomUUID().toString();
    }

    public Candidate(String name, String surname, String eMail, String university, String city, List projects, List favoriteJobs, List appliedJobs) {
        this.id = UUID.randomUUID().toString();
        setCandidateFields(name, surname, eMail, university, city, projects, favoriteJobs, appliedJobs);
    }

    public void updateCandidate(String name, String surname, String eMail, String university, String city, List projects, List favoriteJobs, List appliedJobs) {
        setCandidateFields(name, surname, eMail, university, city, projects, favoriteJobs, appliedJobs);
    }

    public void setCandidateFields(String name, String surname, String eMail, String university, String city, List projects, List favoriteJobs, List appliedJobs){
        this.name = name;
        this.surname = surname;
        this.eMail = eMail;
        this.university = university;
        this.city = city;
        this.projects = projects;
        this.favoriteJobs = favoriteJobs;
        this.appliedJobs = appliedJobs;
    }

    public void addFavoriteJob(String jobID) {
        // Check if this job exist in the favorite jobs list
        if(!this.favoriteJobs.contains(jobID)){
            this.favoriteJobs.add(jobID);
        }else{
            throw new IllegalArgumentException("This job is already exist in favorite jobs list!");
        }
    }

    public void deleteAJobFromFavoriteJobs(String jobID) {
        if(!this.favoriteJobs.contains(jobID)){
            throw new IllegalArgumentException("This job is not exist in favorite jobs list!");
        }
        this.favoriteJobs.remove(jobID);
    }

    public void addToAppliedJobs(String jobID) {
        // Check if this job exist in the applied jobs list
        if(!this.appliedJobs.contains(jobID)){
            this.appliedJobs.add(jobID);
            // job servisini tetikle, ve o jab'a candidateID'yi ekle

        }else{
            throw new IllegalArgumentException("This job is already exist in applied job list!");
        }
    }

    public void deleteAJobFromAppliedJobs(String jobID) {
        if(!this.appliedJobs.contains(jobID)){
            throw new IllegalArgumentException("This job is not exist in applied jobs list!");
        }
        this.appliedJobs.remove(jobID);
    }
}
