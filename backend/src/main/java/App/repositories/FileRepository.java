package App.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import App.models.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
	@Query("SELECT f FROM File f WHERE f.teachingMaterial.subjectRealization.subject.id = ?1")
	Iterable<Optional<File>> getAllBySubject(Long id);
}
