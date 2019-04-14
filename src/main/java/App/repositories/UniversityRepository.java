package App.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import App.models.University;

@Repository
public interface UniversityRepository extends CrudRepository<University, Long> {

}