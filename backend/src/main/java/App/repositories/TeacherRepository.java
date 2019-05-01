package App.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import App.models.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	@Query("SELECT t FROM Teacher t WHERE t.personalData.firstname LIKE ?1")
	Iterable<Optional<Teacher>> findTeachersByName(String name);
	@Query("SELECT t FROM Teacher t WHERE t.personalData.personalNumber = ?1")
	Optional<Teacher> findTeacherByJmbg(String jmbg);
}