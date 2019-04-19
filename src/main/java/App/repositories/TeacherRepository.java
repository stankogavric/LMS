package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}