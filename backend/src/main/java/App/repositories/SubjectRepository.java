package App.repositories;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
	Iterable<Optional<Subject>> findByNameLike(String name);
	ArrayList<Subject> findByYearOfStudyIdEquals(Long yearOfStudyId);
}