package com.trendyolbootcamp.linkedinclonecandidate;

import com.trendyolbootcamp.linkedinclonecandidate.FakeData.FakeCandidate;
import com.trendyolbootcamp.linkedinclonecandidate.domain.Candidate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;


public class CandidateTests {

    Candidate sut;

    @BeforeEach
    public void setup() {
        sut = new Candidate(FakeCandidate.name, FakeCandidate.surname, FakeCandidate.eMail, FakeCandidate.university, FakeCandidate.city, FakeCandidate.projects, FakeCandidate.favoriteJobs, FakeCandidate.appliedJobs);
    }

    @Test
    public void it_should_update_a_candidate(){
        // Arrange
        String surnameToUpdate = "Aydın";

        // Act
        sut.updateCandidate(FakeCandidate.name, surnameToUpdate, FakeCandidate.eMail, FakeCandidate.university, FakeCandidate.city, FakeCandidate.projects, FakeCandidate.favoriteJobs, FakeCandidate.appliedJobs);

        // Assert
        assertThat(surnameToUpdate).isEqualTo(sut.getSurname());
    }

    @Test
    public void it_should_add_new_favorite_job(){
        // Arrange
        String jobID = "123";
        int favJobSize = sut.getFavoriteJobs().size();

        // Act
        sut.addFavoriteJob(jobID);

        // Assert
        assertThat(sut.getFavoriteJobs().size()).isEqualTo(favJobSize+1);
    }

    @Test
    public void it_should_not_add_favorite_job_if_it_is_exist(){
        // Arrange
        String jobID = "123";
        sut.getFavoriteJobs().add(jobID);
        int favJobSize = sut.getFavoriteJobs().size();

        // Act and Assert
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> sut.addFavoriteJob(jobID));
        assertThat("This job is already exist in favorite jobs list!").isEqualTo(exception.getMessage());
    }


    @Test
    public void it_should_delete_a_job_from_favorite_jobs(){
        // Arrange
        int favJobSize = sut.getFavoriteJobs().size();
        String jobID = "123";
        sut.getFavoriteJobs().add(jobID);

        // Act
        sut.deleteAJobFromFavoriteJobs(jobID);

        // Assert
        assertThat(sut.getFavoriteJobs().size()).isEqualTo(favJobSize);
    }

    @Test
    public void it_should_throw_error_if_job_is_not_exist_in_list_when_deleting_job_from_favorites(){
        // Arrange
        int favJobSize = sut.getFavoriteJobs().size();
        String jobID = "123";
        sut.getFavoriteJobs().add(jobID);
        String jobIDtoDelete = "449165223";

        // Act and Assert
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> sut.deleteAJobFromFavoriteJobs(jobIDtoDelete));
        assertThat("This job is not exist in favorite jobs list!").isEqualTo(exception.getMessage());
    }

    @Test
    public void it_should_add_job_to_applied_jobs(){
        // Arrange
        String jobID = "5132";
        int appliedJobSize = sut.getAppliedJobs().size();

        // Act
        sut.addToAppliedJobs(jobID);

        // Assert
        assertThat(sut.getAppliedJobs().size()).isEqualTo(appliedJobSize+1);
    }

    @Test
    public void it_should_delete_a_job_from_applied_jobs(){
        // Arrange
        int appliedJobSize = sut.getAppliedJobs().size();
        String jobID = "3265";
        sut.getAppliedJobs().add(jobID);

        // Act
        sut.deleteAJobFromAppliedJobs(jobID);

        // Assert
        assertThat(sut.getAppliedJobs().size()).isEqualTo(appliedJobSize);
    }

}
