package App.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import App.models.TitleType;

@Repository
public interface TitleTypeRepository extends CrudRepository<TitleType, Long> {

}