package App.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import App.models.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {

}