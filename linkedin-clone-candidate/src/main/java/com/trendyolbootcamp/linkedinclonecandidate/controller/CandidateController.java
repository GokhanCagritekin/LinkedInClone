package com.trendyolbootcamp.linkedinclonecandidate.controller;
import com.trendyolbootcamp.linkedinclonecandidate.domain.Candidate;
import com.trendyolbootcamp.linkedinclonecandidate.service.CandidateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping("/{candidateID}")
    public ResponseEntity<Candidate> getCandidateByID(@PathVariable String candidateID){
        return ResponseEntity.ok(candidateService.getCandidateByID(candidateID));
    }

    @PostMapping
    public ResponseEntity<Void> placeCandidate(@RequestBody Candidate candidate){
        candidateService.placeCandidate(candidate);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{candidateID}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable String candidateID){
        candidateService.deleteCandidate(candidateID);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{candidateID}")
    public ResponseEntity<Void> updateCandidate(@PathVariable String candidateID,
                                                @RequestParam Optional<String> name,
                                                @RequestParam Optional<String> surname,
                                                @RequestParam Optional<String> email,
                                                @RequestParam Optional<String> university,
                                                @RequestParam Optional<String> city,
                                                @RequestParam Optional<List> projects,
                                                @RequestParam Optional<List> favoriteJobs,
                                                @RequestParam Optional<List> appliedJobs){
        candidateService.updateCandidate(candidateID, name, surname, email, university, city, projects, favoriteJobs, appliedJobs);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listAllCandidates")
    public ResponseEntity<List<Candidate>> listCandidates(){
            return ResponseEntity.ok(candidateService.findAll());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Candidate>> filterCandidates(@RequestParam Optional<String> university,
                                                            @RequestParam Optional<String> city){
        return ResponseEntity.ok(candidateService.filterCandidates(university, city));
    }

    @PostMapping("apply/{candidateID}/{jobID}")
    public ResponseEntity<Void> addToAppliedJobs(@PathVariable String candidateID, @PathVariable String jobID){
        candidateService.addToAppliedJobs(candidateID, jobID);
        return ResponseEntity.ok().build();
    }

    @PostMapping("favorite/{candidateID}/{jobID}")
    public ResponseEntity<Void> addToFavoriteJobs(@PathVariable String candidateID, @PathVariable String jobID){
        candidateService.addToFavoriteJobs(candidateID, jobID);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("favorite/{candidateID}/{jobID}")
    public ResponseEntity<Void> deleteAJobFromFavoriteJobs(@PathVariable String candidateID, @PathVariable String jobID){
        candidateService.deleteAJobFromFavoriteJobs(candidateID, jobID);
        return ResponseEntity.ok().build();
    }

}
