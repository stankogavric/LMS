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

import App.models.City;
import App.services.CityService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    CityService cityService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<City>> getCities() {
        return new ResponseEntity<Iterable<City>>(cityService.getCities(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<City> getCityById(@PathVariable Long id) {
        Optional<City> city = cityService.getCityById(id);
        if(city.isPresent()) {
            return new ResponseEntity<City>(city.get(), HttpStatus.OK);
        }
        return new ResponseEntity<City>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<City> addCity(@RequestBody City Cities) {
        cityService.addCity(Cities);
        return new ResponseEntity<City>(Cities, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<City> updateCity(@PathVariable Long id, @RequestBody City Cities) {
        cityService.updateCity(id, Cities);
        return new ResponseEntity<City>(Cities, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<City> removeCity(@PathVariable Long id) {
        try {
            cityService.removeCity(id);
        }catch (Exception e) {
            return new ResponseEntity<City>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<City>(HttpStatus.NO_CONTENT);
    }

}
