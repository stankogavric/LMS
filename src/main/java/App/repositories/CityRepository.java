package App.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import App.models.City;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

}