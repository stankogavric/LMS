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

import App.models.Administrator;
import App.services.AdministratorService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/administrator")
public class AdministratorController {

    @Autowired
    AdministratorService administratorService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<Administrator>> getAdministrators() {
        return new ResponseEntity<Iterable<Administrator>>(administratorService.getAdministrators(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Administrator> getAdministratorById(@PathVariable Long id) {
        Optional<Administrator> administrator = administratorService.getAdministratorById(id);
        if(administrator.isPresent()) {
            return new ResponseEntity<Administrator>(administrator.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Administrator>(HttpStatus.NOT_FOUND);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/username/{username}", method=RequestMethod.GET)
    public ResponseEntity<Administrator> getAdministratorByUsername(@PathVariable String username) {
        Optional<Administrator> administrator = administratorService.getAdministratorByUsername(username);
        if(administrator.isPresent()) {
            return new ResponseEntity<Administrator>(administrator.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Administrator>(HttpStatus.NOT_FOUND);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public ResponseEntity<Administrator> addAdministrator(@RequestBody Administrator Administrators) {
        administratorService.addAdministrator(Administrators);
        return new ResponseEntity<Administrator>(Administrators, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{username}", method=RequestMethod.PUT)
    public ResponseEntity<Administrator> updateAdministrator(@PathVariable String username, @RequestBody Administrator Administrators) {
        administratorService.updateAdministrator(username, Administrators);
        return new ResponseEntity<Administrator>(Administrators, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Administrator> removeAdministrator(@PathVariable Long id) {
        try {
            administratorService.removeAdministrator(id);
        }catch (Exception e) {
            return new ResponseEntity<Administrator>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Administrator>(HttpStatus.NO_CONTENT);
    }

}
