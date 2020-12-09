package com.trendyolbootcamp.linkedinclonecompany.repository;

import com.trendyolbootcamp.linkedinclonecompany.domain.Company;
import com.trendyolbootcamp.linkedinclonecompany.service.CompanyService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;


@ExtendWith(MockitoExtension.class)
public class CompanyRepositoryTest {


/*
    private Company company;


    @InjectMocks
    private CompanyRepository companyRepository;


    @BeforeEach
    private void beforeEach() {
        company = new Company("Test");
        companyRepository.insert(company);

        Mockito.when(companyRepository.findById(company.getId()))
                .thenReturn(company);
        initMocks(this);

    }


    @Test
    public void it_should_insert_new_company() {
        assertNotNull(company);
    }

    @Test
    public void it_should_find_all_companies() {
        assertNotNull(companyRepository.findAll());
    }

    @Test
    public void it_should_delete_by_id() {
        companyRepository.delete(company.getId());
        assertEquals(true,companyRepository.findAll().isEmpty());
    }


    @Test
    public void it_should_find_by_id() {

    }*/


}
