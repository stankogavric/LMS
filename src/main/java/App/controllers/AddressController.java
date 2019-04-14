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

import App.models.Address;
import App.services.AddressService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<Address>> getAddresses() {
        return new ResponseEntity<Iterable<Address>>(addressService.getAddresses(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        Optional<Address> address = addressService.getAddressById(id);
        if(address.isPresent()) {
            return new ResponseEntity<Address>(address.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<Address> addAddress(@RequestBody Address Addresses) {
        addressService.addAddress(Addresses);
        return new ResponseEntity<Address>(Addresses, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address Addresses) {
        addressService.updateAddress(id, Addresses);
        return new ResponseEntity<Address>(Addresses, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Address> removeAddress(@PathVariable Long id) {
        try {
            addressService.removeAddress(id);
        }catch (Exception e) {
            return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Address>(HttpStatus.NO_CONTENT);
    }

}
