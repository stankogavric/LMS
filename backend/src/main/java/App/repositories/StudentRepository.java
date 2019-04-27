package App.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
//	Optional<Student> findByJmbg(String jmbg);
//	Iterable<Optional<Student>> findByNameLike(String name);
}