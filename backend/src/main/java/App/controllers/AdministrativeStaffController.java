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

import App.models.AdministrativeStaff;
import App.services.AdministrativeStaffService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/administrativestaff")
public class AdministrativeStaffController {

    @Autowired
    AdministrativeStaffService administrativeStaffService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<AdministrativeStaff>> getAdministrativeStaff() {
        return new ResponseEntity<Iterable<AdministrativeStaff>>(administrativeStaffService.getAdministrativeStaff(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<AdministrativeStaff> getAdministrativeStaffById(@PathVariable Long id) {
        Optional<AdministrativeStaff> administrativeStaff = administrativeStaffService.getAdministrativeStaffById(id);
        if(administrativeStaff.isPresent()) {
            return new ResponseEntity<AdministrativeStaff>(administrativeStaff.get(), HttpStatus.OK);
        }
        return new ResponseEntity<AdministrativeStaff>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<AdministrativeStaff> addAdministrativeStaff(@RequestBody AdministrativeStaff AdministrativeStaff) {
        administrativeStaffService.addAdministrativeStaff(AdministrativeStaff);
        return new ResponseEntity<AdministrativeStaff>(AdministrativeStaff, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<AdministrativeStaff> updateAdministrativeStaff(@PathVariable Long id, @RequestBody AdministrativeStaff AdministrativeStaff) {
        administrativeStaffService.updateAdministrativeStaff(id, AdministrativeStaff);
        return new ResponseEntity<AdministrativeStaff>(AdministrativeStaff, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<AdministrativeStaff> removeAdministrativeStaff(@PathVariable Long id) {
        try {
            administrativeStaffService.removeAdministrativeStaff(id);
        }catch (Exception e) {
            return new ResponseEntity<AdministrativeStaff>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<AdministrativeStaff>(HttpStatus.NO_CONTENT);
    }

}
