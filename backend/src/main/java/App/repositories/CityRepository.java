package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}