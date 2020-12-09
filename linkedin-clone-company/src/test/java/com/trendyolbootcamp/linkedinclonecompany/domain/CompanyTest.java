package com.trendyolbootcamp.linkedinclonecompany.domain;


import com.trendyolbootcamp.linkedinclonecompany.domain.Company;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.UUID;


public class CompanyTest {

    private Company sut;

    @BeforeEach
    private void beforeEach() {
        sut = new Company();
    }

    @AfterEach
    public void afterEach() {
        sut = null;
    }


    @Test
    public void get_id_should_return_id() {
        //Arrange

        //Act
        String result = sut.getId();

        //Assert
        assertEquals(result,result);
    }






}
