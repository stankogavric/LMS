package App.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import App.models.Country;
import App.services.CountryService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    CountryService countryService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<Country>> getCountries() {
        return new ResponseEntity<Iterable<Country>>(countryService.getCountries(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Country> getCountryById(@PathVariable Long id) {
        Optional<Country> country = countryService.getCountryById(id);
        if(country.isPresent()) {
            return new ResponseEntity<Country>(country.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<Country> addCountry(@RequestBody Country Countries) {
        countryService.addCountry(Countries);
        return new ResponseEntity<Country>(Countries, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Country> updateCountry(@PathVariable Long id, @RequestBody Country Countries) {
        countryService.updateCountry(id, Countries);
        return new ResponseEntity<Country>(Countries, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Country> removeCountry(@PathVariable Long id) {
        try {
            countryService.removeCountry(id);
        }catch (Exception e) {
            return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Country>(HttpStatus.NO_CONTENT);
    }

}
