package App.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.UniversityEmails;

@Repository
public interface UniversityEmailsRepository extends JpaRepository<UniversityEmails, Long> {
	ArrayList<UniversityEmails> findByUniversityIdEquals(Long id);
}
