package App.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	Iterable<Optional<Teacher>> findByNameLike(String name);
	Optional<Teacher> findByJmbg(String jmbg);
}