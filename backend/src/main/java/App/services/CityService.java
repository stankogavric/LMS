package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.City;
import App.repositories.CityRepository;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepo;

    public CityService() {
    }

    public Iterable<City> getCities() {
        return cityRepo.findAll();
    }

    public Optional<City> getCityById(Long id) {
        return cityRepo.findById(id);
    }

    public void addCity(City city) {
        cityRepo.save(city);
    }

    public void removeCity(Long id) {
        Optional<City> city = cityRepo.findById(id);
        cityRepo.delete(city.get());
    }

    public void updateCity(Long id, City city) {
        Optional<City> Cit = cityRepo.findById(id);
        if(Cit.isPresent()) {
            city.setId(Cit.get().getId());
            cityRepo.save(city);
        }
    }

}
