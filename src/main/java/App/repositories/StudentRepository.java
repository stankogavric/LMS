package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}