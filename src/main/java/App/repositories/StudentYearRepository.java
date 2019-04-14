package App.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import App.models.StudentYear;

@Repository
public interface StudentYearRepository extends CrudRepository<StudentYear, Long> {

}