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

import App.models.TeachingType;
import App.services.TeachingTypeService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/teachingtype")
public class TeachingTypeController {

    @Autowired
    TeachingTypeService teachingTypeService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<TeachingType>> getTeachingTypes() {
        return new ResponseEntity<Iterable<TeachingType>>(teachingTypeService.getTeachingTypes(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<TeachingType> getTeachingTypeById(@PathVariable Long id) {
        Optional<TeachingType> teachingType = teachingTypeService.getTeachingTypeById(id);
        if(teachingType.isPresent()) {
            return new ResponseEntity<TeachingType>(teachingType.get(), HttpStatus.OK);
        }
        return new ResponseEntity<TeachingType>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<TeachingType> addTeachingType(@RequestBody TeachingType TeachingTypes) {
        teachingTypeService.addTeachingType(TeachingTypes);
        return new ResponseEntity<TeachingType>(TeachingTypes, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<TeachingType> updateTeachingType(@PathVariable Long id, @RequestBody TeachingType TeachingTypes) {
        teachingTypeService.updateTeachingType(id, TeachingTypes);
        return new ResponseEntity<TeachingType>(TeachingTypes, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<TeachingType> removeTeachingType(@PathVariable Long id) {
        try {
            teachingTypeService.removeTeachingType(id);
        }catch (Exception e) {
            return new ResponseEntity<TeachingType>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<TeachingType>(HttpStatus.NO_CONTENT);
    }

}
