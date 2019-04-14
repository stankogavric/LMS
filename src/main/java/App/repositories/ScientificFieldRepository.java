package App.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import App.models.ScientificField;

@Repository
public interface ScientificFieldRepository extends CrudRepository<ScientificField, Long> {

}