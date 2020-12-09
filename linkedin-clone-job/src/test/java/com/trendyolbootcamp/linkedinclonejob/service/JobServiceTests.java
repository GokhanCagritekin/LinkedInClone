package com.trendyolbootcamp.linkedinclonejob.service;

import com.trendyolbootcamp.linkedinclonejob.domain.Job;
import com.trendyolbootcamp.linkedinclonejob.repository.JobRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class JobServiceTests {
    @Mock
    JobRepository jobRepository;

    @InjectMocks
    JobService jobService;

    @Test
    public void it_should_find_job_ById(){
        when(jobRepository.findById("1")).thenReturn(new Job());

        Job job = jobService.getJobById("1");

        assertNotNull(job);
    }

    @Test
    public void it_should_getAllJobs(){
        when(jobRepository.findAll()).thenReturn(new ArrayList<>());

        List<Job> job = jobService.findAll();

        assertNotNull(job);
    }

}
