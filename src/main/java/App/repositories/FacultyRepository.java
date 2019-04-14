package App.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import App.models.Faculty;

@Repository
public interface FacultyRepository extends CrudRepository<Faculty, Long> {

}