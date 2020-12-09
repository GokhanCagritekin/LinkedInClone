package com.trendyolbootcamp.linkedinclonecompany.controller;


import com.trendyolbootcamp.linkedinclonecompany.domain.Company;
import com.trendyolbootcamp.linkedinclonecompany.service.CompanyService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @PostMapping
    public ResponseEntity<Void> insertCompany(@RequestBody Company company){
        companyService.insertCompany(company);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<Void> deleteCompany(@PathVariable String companyId){
        companyService.deleteCompany(companyId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<Company> getCompanyWithId(@PathVariable String companyId){
        return ResponseEntity.ok(companyService.getCompanyWithId(companyId));
    }

    @PatchMapping("/{companyID}")
    public ResponseEntity<Void> updateCompany(@PathVariable String companyID,
                                                @RequestParam Optional<String> name,
                                                @RequestParam Optional<String> city,
                                                @RequestParam Optional<String> companyExplanation,
                                                @RequestParam Optional<Integer> numberOfTotalEmployee,
                                                @RequestParam Optional<String> sector,
                                                @RequestParam Optional<List> JobPostings){
        companyService.updateCompany(companyID, name, city, companyExplanation, numberOfTotalEmployee, sector, JobPostings);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Company>> filterCompanies(@RequestParam Optional<String> sector, @RequestParam Optional<String> city){
        return ResponseEntity.ok(companyService.filterCompanies(sector, city));
    }

    @GetMapping("/listAllCompanies")
    public ResponseEntity<List<Company>> listCompanies(){
        return ResponseEntity.ok(companyService.findAll());
    }

    @PatchMapping("/{companyID}/{jobID}")
    public ResponseEntity<Void> postJob(@PathVariable String companyID, @PathVariable String jobID){
        companyService.postAJob(companyID, jobID);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listAllJobPostings/{companyID}")
    public ResponseEntity<List<String>> listAllJobPostings(@PathVariable String companyID){
        return ResponseEntity.ok(companyService.listAllJobPostings(companyID));
    }

}