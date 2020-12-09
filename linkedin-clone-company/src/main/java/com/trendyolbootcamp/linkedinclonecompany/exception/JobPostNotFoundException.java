package com.trendyolbootcamp.linkedinclonecompany.exception;

public class JobPostNotFoundException extends Exception{

    public JobPostNotFoundException(String message){
        super("Job post with id not found in that company.");
    }
}
