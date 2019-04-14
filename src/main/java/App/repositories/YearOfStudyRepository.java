package App.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import App.models.YearOfStudy;

@Repository
public interface YearOfStudyRepository extends CrudRepository<YearOfStudy, Long> {

}