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

import App.models.TeachingTerm;
import App.services.TeachingTermService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/teachingterm")
public class TeachingTermController {
	@Autowired
    TeachingTermService teachingTermService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<TeachingTerm>> getTeachingTerms() {
        return new ResponseEntity<Iterable<TeachingTerm>>(teachingTermService.getTeachingTerms(), HttpStatus.OK);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/yearOfStudy/{yearOfStudyId}", method=RequestMethod.GET)
    public ResponseEntity<Iterable<Optional<TeachingTerm>>> getTeachingTermsByYearOfStudy(@PathVariable Long yearOfStudyId) {
        return new ResponseEntity<Iterable<Optional<TeachingTerm>>>(teachingTermService.getTeachingTermsByYearOfStudy(yearOfStudyId), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<TeachingTerm> getTeachingTermById(@PathVariable Long id) {
        Optional<TeachingTerm> teachingTerm = teachingTermService.getTeachingTermById(id);
        if(teachingTerm.isPresent()) {
            return new ResponseEntity<TeachingTerm>(teachingTerm.get(), HttpStatus.OK);
        }
        return new ResponseEntity<TeachingTerm>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<TeachingTerm> addTeachingTerm(@RequestBody TeachingTerm TeachingTerms) {
        teachingTermService.addTeachingTerm(TeachingTerms);
        return new ResponseEntity<TeachingTerm>(TeachingTerms, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<TeachingTerm> updateTeachingTerm(@PathVariable Long id, @RequestBody TeachingTerm TeachingTerms) {
        teachingTermService.updateTeachingTerm(id, TeachingTerms);
        return new ResponseEntity<TeachingTerm>(TeachingTerms, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<TeachingTerm> removeTeachingTerm(@PathVariable Long id) {
        try {
            teachingTermService.removeTeachingTerm(id);
        }catch (Exception e) {
            return new ResponseEntity<TeachingTerm>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<TeachingTerm>(HttpStatus.NO_CONTENT);
    }

}