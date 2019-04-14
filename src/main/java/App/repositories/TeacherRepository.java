package App.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import App.models.Teacher;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {

}