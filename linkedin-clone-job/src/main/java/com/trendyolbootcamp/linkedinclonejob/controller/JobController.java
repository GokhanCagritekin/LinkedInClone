package com.trendyolbootcamp.linkedinclonejob.controller;

import com.trendyolbootcamp.linkedinclonejob.domain.Job;
import com.trendyolbootcamp.linkedinclonejob.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/job")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<Void> placeJob(@RequestBody Job job) {
        jobService.placeJob(job);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Job>> listJobs() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findJobById(@PathVariable String id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity <Void>  updateJob(@PathVariable String id,
                                            @RequestParam Optional<String> title,
                                            @RequestParam Optional<String> sector,
                                            @RequestParam Optional<String> city,
                                            @RequestParam Optional<String> company,
                                            @RequestParam Optional<Integer> numberOfHires,
                                            @RequestParam Optional<List> requirements,
                                            @RequestParam Optional<List> applicants){
        jobService.updateJob(id, title, sector, city, company,numberOfHires, requirements, applicants);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity deleteJob(@PathVariable String id){
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Job>> filterJobs(@RequestParam Optional<String> sector,
                @RequestParam Optional<String> city,
                @RequestParam Optional<String> company){
            return ResponseEntity.ok(jobService.filterJobs(sector, city, company));
    }

    @RequestMapping(value="/{id}/applicants", method = RequestMethod.GET)
    public ResponseEntity <List> getApplicants(@PathVariable String id){
        return  ResponseEntity.ok(jobService.getJobApplicants(id));
    }

    @PatchMapping("/{id}/{candidateId}")
    public ResponseEntity<Void> addApplicant(@PathVariable String id,
                                             @PathVariable String candidateId){
        jobService.addJobApplicant(id,candidateId);
        return ResponseEntity.ok().build();
    }

}
