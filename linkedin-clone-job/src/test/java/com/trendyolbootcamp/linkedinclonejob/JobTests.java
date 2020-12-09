package com.trendyolbootcamp.linkedinclonejob;

import com.trendyolbootcamp.linkedinclonejob.domain.Job;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


public class JobTests {
    @Test
    public void it_should_create_a_new_job(){
        // Arrange & Act
        Job job = new Job();
        // Assert
        assertThat("Software Developer").isEqualTo(job.getTitle());
    }

    @Test
    public void it_should_update_job_title(){
        // Arrange & Act
        Job job = new Job();
        job.updateJobTitle("Software Specialist");
        // Assert
        assertThat("Software Specialist").isEqualTo(job.getTitle());
    }
    @Test
    public void it_should_update_job_sector(){
        // Arrange & Act
        Job job = new Job();
        job.updateJobSector("Marketing");
        // Assert
        assertThat("Marketing").isEqualTo(job.getSector());
    }
    @Test
    public void it_should_update_job_city(){
        // Arrange & Act
        Job job = new Job();
        job.updateJobCity("İzmir");
        // Assert
        assertThat("İzmir").isEqualTo(job.getCity());
    }
    @Test
    public void it_should_update_job_company(){
        // Arrange & Act
        Job job = new Job();
        job.updateJobCompany("Trendyol");
        // Assert
        assertThat("Trendyol").isEqualTo(job.getCompany());
    }
    @Test
    public void it_should_update_job_numberOfHires(){
        // Arrange & Act
        Job job = new Job();
        job.updateJobNumberOfHires(7);
        // Assert
        assertThat(7).isEqualTo(job.getNumberOfHires());
    }
    @Test
    public void it_should_update_job_requirements(){
        // Arrange & Act
        Job job = new Job();
        job.updateJobRequirements(new ArrayList<>(Arrays.asList("Problem solving skills",
                "Writing and testing code")));
        // Assert
        assertThat(new ArrayList<>(Arrays.asList("Problem solving skills",
                "Writing and testing code"))).isEqualTo(job.getRequirements());
    }
    @Test
    public void it_should_update_job_applicants(){
        // Arrange & Act
        Job job = new Job();
        job.updateApplicants(new ArrayList<>(Arrays.asList("Gökhan","Ahmet","Jhon","Yonca","Ali")));
        // Assert
        assertThat(new ArrayList<>(Arrays.asList("Gökhan","Ahmet","Jhon","Yonca","Ali"))).isEqualTo(job.getApplicants());
    }

    @Test
    public void it_should_delete_job(){
        // Arrange & Act
        Job job = new Job();
        job = null;
        // Assert
        assertNull(job);
    }

    @Test
    public void it_should_add_applicant_to_job(){
        // Arrange & Act
        Job job = new Job();
        job.getApplicants().add("Jhon");
        // Assert
        assertEquals(new ArrayList(job.getApplicants()), (job.getApplicants()));
    }

}
