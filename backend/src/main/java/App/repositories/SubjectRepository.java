package App.repositories;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import App.models.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
	Iterable<Optional<Subject>> findByNameLike(String name);
	ArrayList<Subject> findByYearOfStudyIdEquals(Long yearOfStudyId);
	@Query("SELECT p FROM Subject s JOIN s.prerequisites p WHERE s.mandatory = True AND s.yearOfStudy.id = ?1")
	Iterable<Subject> getPrerequisitesForMandatorySubjectsByYearOfStudy(Long yearOfStudyId);
}