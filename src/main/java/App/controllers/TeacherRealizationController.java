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

import App.models.TeacherRealization;
import App.services.TeacherRealizationService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/teacherrealization")
public class TeacherRealizationController {

    @Autowired
    TeacherRealizationService teacherRealizationService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<TeacherRealization>> getTeacherRealizations() {
        return new ResponseEntity<Iterable<TeacherRealization>>(teacherRealizationService.getTeacherRealizations(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<TeacherRealization> getTeacherRealizationById(@PathVariable Long id) {
        Optional<TeacherRealization> teacherRealization = teacherRealizationService.getTeacherRealizationById(id);
        if(teacherRealization.isPresent()) {
            return new ResponseEntity<TeacherRealization>(teacherRealization.get(), HttpStatus.OK);
        }
        return new ResponseEntity<TeacherRealization>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<TeacherRealization> addTeacherRealization(@RequestBody TeacherRealization TeacherRealizations) {
        teacherRealizationService.addTeacherRealization(TeacherRealizations);
        return new ResponseEntity<TeacherRealization>(TeacherRealizations, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<TeacherRealization> updateTeacherRealization(@PathVariable Long id, @RequestBody TeacherRealization TeacherRealizations) {
        teacherRealizationService.updateTeacherRealization(id, TeacherRealizations);
        return new ResponseEntity<TeacherRealization>(TeacherRealizations, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<TeacherRealization> removeTeacherRealization(@PathVariable Long id) {
        try {
            teacherRealizationService.removeTeacherRealization(id);
        }catch (Exception e) {
            return new ResponseEntity<TeacherRealization>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<TeacherRealization>(HttpStatus.NO_CONTENT);
    }

}
