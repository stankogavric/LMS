package App.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.FacultyEmails;

@Repository
public interface FacultyEmailsRepository extends JpaRepository<FacultyEmails, Long> {
	ArrayList<FacultyEmails> findByFacultyIdEquals(Long id);
}