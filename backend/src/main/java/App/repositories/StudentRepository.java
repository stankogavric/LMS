package App.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import App.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	@Query("SELECT s FROM Student s WHERE s.personalData.personalNumber = ?1")
	Optional<Student> findStudentByJmbg(String jmbg);
	@Query("SELECT s FROM Student s WHERE s.personalData.name LIKE ?1")
	Iterable<Optional<Student>> findStudentsByName(String name);
}