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

import App.models.SubjectRealization;
import App.models.Teacher;
import App.services.SubjectRealizationService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/subjectrealization")
public class SubjectRealizationController {

    @Autowired
    SubjectRealizationService subjectRealizationService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<SubjectRealization>> getSubjectRealization() {
        return new ResponseEntity<Iterable<SubjectRealization>>(subjectRealizationService.getSubjectRealization(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<SubjectRealization> getSubjectRealizationById(@PathVariable Long id) {
        Optional<SubjectRealization> subjectRealization = subjectRealizationService.getSubjectRealizationById(id);
        if(subjectRealization.isPresent()) {
            return new ResponseEntity<SubjectRealization>(subjectRealization.get(), HttpStatus.OK);
        }
        return new ResponseEntity<SubjectRealization>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<SubjectRealization> addSubjectRealization(@RequestBody SubjectRealization SubjectRealization) {
        subjectRealizationService.addSubjectRealization(SubjectRealization);
        return new ResponseEntity<SubjectRealization>(SubjectRealization, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<SubjectRealization> updateSubjectRealization(@PathVariable Long id, @RequestBody SubjectRealization SubjectRealization) {
        subjectRealizationService.updateSubjectRealization(id, SubjectRealization);
        return new ResponseEntity<SubjectRealization>(SubjectRealization, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<SubjectRealization> removeSubjectRealization(@PathVariable Long id) {
        try {
            subjectRealizationService.removeSubjectRealization(id);
        }catch (Exception e) {
            return new ResponseEntity<SubjectRealization>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<SubjectRealization>(HttpStatus.NO_CONTENT);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/teachersWhoTeachExercises/{subjectId}", method=RequestMethod.GET)
    public ResponseEntity<Iterable<Teacher>> getTeachersWhoTeachExercises(@PathVariable Long subjectId) {
        return new ResponseEntity<Iterable<Teacher>>(subjectRealizationService.getTeachersWhoTeachExercises(subjectId), HttpStatus.OK);
    }

}
