package App.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import App.models.TeachingMaterial;

@Repository
public interface TeachingMaterialRepository extends JpaRepository<TeachingMaterial, Long> {
	@Query("SELECT t FROM TeachingMaterial t WHERE t.subjectRealization.subject.id = ?1")
	Iterable<Optional<TeachingMaterial>> getAllBySubject(Long id);
}
