package App.controllers;

import java.util.ArrayList;
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

import App.models.Subject;
import App.models.Topic;
import App.services.SubjectService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<Subject>> getSubjects() {
        return new ResponseEntity<Iterable<Subject>>(subjectService.getSubjects(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Subject> getSubjectById(@PathVariable Long id) {
        Optional<Subject> subject = subjectService.getSubjectById(id);
        if(subject.isPresent()) {
            return new ResponseEntity<Subject>(subject.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Subject>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<Subject> addSubject(@RequestBody Subject Subjects) {
        subjectService.addSubject(Subjects);
        return new ResponseEntity<Subject>(Subjects, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Subject> updateSubject(@PathVariable Long id, @RequestBody Subject Subjects) {
        subjectService.updateSubject(id, Subjects);
        return new ResponseEntity<Subject>(Subjects, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Subject> removeSubject(@PathVariable Long id) {
        try {
            subjectService.removeSubject(id);
        }catch (Exception e) {
            return new ResponseEntity<Subject>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Subject>(HttpStatus.NO_CONTENT);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/findByName/{name}", method=RequestMethod.GET)
    public ResponseEntity<Iterable<Optional<Subject>>> getSubjectsByName(@PathVariable String name) {
        Iterable<Optional<Subject>> subjects = subjectService.getSubjectsByName(name);
        return new ResponseEntity<Iterable<Optional<Subject>>>(subjects, HttpStatus.OK);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/syllabuses/{subjectId}", method=RequestMethod.GET)
    public ResponseEntity<ArrayList<Topic>> getSyllabuses(@PathVariable Long subjectId) {
        return new ResponseEntity<ArrayList<Topic>>(subjectService.getSyllabuses(subjectId), HttpStatus.OK);
    }

}
