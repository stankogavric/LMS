package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.Country;
import App.repositories.CountryRepository;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepo;

    public CountryService() {
    }

    public Iterable<Country> getCountries() {
        return countryRepo.findAll();
    }

    public Optional<Country> getCountryById(Long id) {
        return countryRepo.findById(id);
    }

    public void addCountry(Country country) {
        countryRepo.save(country);
    }

    public void removeCountry(Long id) {
        Optional<Country> country = countryRepo.findById(id);
        countryRepo.delete(country.get());
    }

    public void updateCountry(Long id, Country country) {
        Optional<Country> Cou = countryRepo.findById(id);
        if(Cou.isPresent()) {
            country.setId(Cou.get().getId());
            countryRepo.save(country);
        }
    }

}
