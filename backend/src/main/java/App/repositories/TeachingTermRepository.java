package App.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import App.models.TeachingTerm;

@Repository
public interface TeachingTermRepository extends JpaRepository<TeachingTerm, Long> {
	@Query("SELECT tt FROM TeachingTerm tt WHERE tt.subjectRealization.yearOfStudy.id = ?1")
	Iterable<Optional<TeachingTerm>> getByYearOfStudy(Long yearOfStudyId);
}
