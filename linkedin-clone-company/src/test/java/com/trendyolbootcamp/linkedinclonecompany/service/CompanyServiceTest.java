package com.trendyolbootcamp.linkedinclonecompany.service;

import com.trendyolbootcamp.linkedinclonecompany.domain.Company;
import com.trendyolbootcamp.linkedinclonecompany.exception.CompanyNotFoundException;
import com.trendyolbootcamp.linkedinclonecompany.repository.CompanyRepository;
import org.assertj.core.api.Assert;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    @InjectMocks
    private CompanyService sut;
    private Company company;

    @Mock
    private CompanyRepository companyRepository;


    @BeforeEach
    private void beforeEach() {
        initMocks(this);
        company = new Company("Test");
    }

    @AfterEach
    private void afterEach(){
        sut = null;
    }




    @Test
    public void it_should_throw_CompanyNotFoundException_when_there_is_no_company(){
     
        // Arrange

        // Act

        // Assert
        assertThrows(CompanyNotFoundException.class, () -> sut.getCompanyWithId(company.getId()));

    }


    @Test
    public void should_return_company_with_getById(){


    }

    @Test
    public void listAllJobPostings_should_return_allJobPostings(){

        //Arrange
        //Act
        //Assert

    }


    @Test
    public void getJobPostWithId_method_should_return_correct_jobPosting(){

        //Arrange
        //Act
        //Assert

    }


    @Test
    public void listAllCandidateForJobPostGivenId_method_should_return_allCandidate_that_applied_the_given_jobPosting(){

        //Arrange
        //Act
        //Assert

    }

    @Test
    public void filter_candidate_by_city(){
        //Arrange
        //Act
        //Assert
    }


    @Test
    public void filter_candidate_by_university(){
        //Arrange
        //Act
        //Assert
    }

}