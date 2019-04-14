package App.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import App.models.Subject;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {

}