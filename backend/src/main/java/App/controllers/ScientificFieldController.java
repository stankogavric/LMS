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

import App.models.ScientificField;
import App.services.ScientificFieldService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/scientificfield")
public class ScientificFieldController {

    @Autowired
    ScientificFieldService scientificFieldService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<ScientificField>> getScientificFields() {
        return new ResponseEntity<Iterable<ScientificField>>(scientificFieldService.getScientificFields(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<ScientificField> getScientificFieldById(@PathVariable Long id) {
        Optional<ScientificField> scientificField = scientificFieldService.getScientificFieldById(id);
        if(scientificField.isPresent()) {
            return new ResponseEntity<ScientificField>(scientificField.get(), HttpStatus.OK);
        }
        return new ResponseEntity<ScientificField>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<ScientificField> addScientificField(@RequestBody ScientificField ScientificFields) {
        scientificFieldService.addScientificField(ScientificFields);
        return new ResponseEntity<ScientificField>(ScientificFields, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<ScientificField> updateScientificField(@PathVariable Long id, @RequestBody ScientificField ScientificFields) {
        scientificFieldService.updateScientificField(id, ScientificFields);
        return new ResponseEntity<ScientificField>(ScientificFields, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<ScientificField> removeScientificField(@PathVariable Long id) {
        try {
            scientificFieldService.removeScientificField(id);
        }catch (Exception e) {
            return new ResponseEntity<ScientificField>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ScientificField>(HttpStatus.NO_CONTENT);
    }

}
