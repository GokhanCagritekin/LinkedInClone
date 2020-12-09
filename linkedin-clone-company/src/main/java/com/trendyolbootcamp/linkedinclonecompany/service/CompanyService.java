package com.trendyolbootcamp.linkedinclonecompany.service;


import com.trendyolbootcamp.linkedinclonecompany.domain.Company;
import com.trendyolbootcamp.linkedinclonecompany.domain.FakeCandidate;
import com.trendyolbootcamp.linkedinclonecompany.domain.FakeCompany;
import com.trendyolbootcamp.linkedinclonecompany.exception.CompanyNotFoundException;
import com.trendyolbootcamp.linkedinclonecompany.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private static final int TIMEOUT = 1000;
    private final CompanyRepository companyRepository;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    public CompanyService(CompanyRepository companyRepo) {
        this.companyRepository = companyRepo;
    }


    public Company insertCompany(Company company) {
        return companyRepository.insert(company);
    }

    public void deleteCompany(String id){
        companyRepository.delete(id);
    }

    public Company getCompanyWithId(String companyId) {
        Optional<Company> optionalCompany = companyRepository.findByIdOptional(companyId);
        if (!optionalCompany.isPresent()){
            throw new CompanyNotFoundException("Company not found !");
        }
        Company company = optionalCompany.get();

        return company;
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }


    // İlan yayınla (yeni ilan oluştur)
    public void postAJob(String companyID, String jobID){
        Optional<Company> optionalCompany = companyRepository.findByIdOptional(companyID);

        if (!optionalCompany.isPresent()){
            throw new IllegalArgumentException("companyID could not find in the database!");
        }

        Company company = optionalCompany.get();
        company.getJobPostings().add(jobID);
        companyRepository.update(company);
    }

    // Tüm ilanları göster (şirketin ilanları)(verilen idye sahip şirket için)
    public void showAllJobPosting(String id){}

    // Başvuranları görüntüleme (ilanı seç, ona başvuranları görüntüle)
    public void showAllCandidate(String jobPostingId){}

    // Kullanıcı filtreleme (şehrine, okula) (ilan seçip,  başvuranlar arasından filtreleme yap)
    public List<FakeCandidate> filterCandidates(String jobPostingId, Optional<String> university, Optional<String> city) {
        return null;
    }

    public void updateCompany(String companyID, Optional<String> city, Optional<String> companyExplanation,
                              Optional<String> name, Optional<Integer> numberOfTotalEmployee, Optional<String> sector,
                              Optional<List> jobPostings) {
        Optional<Company> optionalCompany = companyRepository.findByIdOptional(companyID);

        if (!optionalCompany.isPresent()){
            // TODO Throw a reasonable exception
            return;
        }

        Company company = optionalCompany.get();
        if (name.isPresent()) company.setName(name.get());
        if (city.isPresent()) company.setCity(city.get());
        if (companyExplanation.isPresent()) company.setCompanyExplanation(companyExplanation.get());
        if (numberOfTotalEmployee.isPresent()) company.setNumberOfTotalEmployee(numberOfTotalEmployee.get());
        if (sector.isPresent()) company.setSector(sector.get());
        if (jobPostings.isPresent()) company.setJobPostings(jobPostings.get());

        companyRepository.update(company);
    }


    public List<Company> filterCompanies(Optional<String> sector, Optional<String> city) {
        return companyRepository.filterCompanies(sector, city);
    }

    public List<String> listAllJobPostings(String companyID) {
        Optional<Company> optionalCompany = companyRepository.findByIdOptional(companyID);

        if (!optionalCompany.isPresent()){
            throw new IllegalArgumentException("companyID could not find in the database!");
        }

        Company company = optionalCompany.get();
        List<String> jobPostings = company.getJobPostings();
        return jobPostings;
    }

    public List<String> getAllApplicants(String jobID) {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(TIMEOUT);
        requestFactory.setReadTimeout(TIMEOUT);
        this.restTemplate.setRequestFactory(requestFactory);

        String requestURL = "http://localhost:8003/job/"+ jobID + "/applicants";
        HttpEntity<String> request = new HttpEntity<>(new String("bar"));
        try {
            return (List<String>) restTemplate.exchange(requestURL, HttpMethod.GET, request, Object.class).getBody();
        } catch (Exception e) {
            throw e;
        }
    }

    public LinkedHashMap getCandidateByID(String candidateID) {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(TIMEOUT);
        requestFactory.setReadTimeout(TIMEOUT);
        this.restTemplate.setRequestFactory(requestFactory);

        String requestURL = "http://localhost:8001/candidate/"+ candidateID;
        HttpEntity<String> request = new HttpEntity<>(new String("bar"));
        try {
            return (LinkedHashMap) restTemplate.exchange(requestURL, HttpMethod.GET, request, Object.class).getBody();
        } catch (Exception e) {
            throw e;
        }
    }
}
