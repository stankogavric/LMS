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
import App.models.YearOfStudy;
import App.services.YearOfStudyService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/yearofstudy")
public class YearOfStudyController {

    @Autowired
    YearOfStudyService yearOfStudyService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<YearOfStudy>> getYearsOfStudy() {
        return new ResponseEntity<Iterable<YearOfStudy>>(yearOfStudyService.getYearsOfStudy(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<YearOfStudy> getYearOfStudyById(@PathVariable Long id) {
        Optional<YearOfStudy> yearOfStudy = yearOfStudyService.getYearOfStudyById(id);
        if(yearOfStudy.isPresent()) {
            return new ResponseEntity<YearOfStudy>(yearOfStudy.get(), HttpStatus.OK);
        }
        return new ResponseEntity<YearOfStudy>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<YearOfStudy> addYearOfStudy(@RequestBody YearOfStudy YearsOfStudy) {
        yearOfStudyService.addYearOfStudy(YearsOfStudy);
        return new ResponseEntity<YearOfStudy>(YearsOfStudy, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<YearOfStudy> updateYearOfStudy(@PathVariable Long id, @RequestBody YearOfStudy YearsOfStudy) {
        yearOfStudyService.updateYearOfStudy(id, YearsOfStudy);
        return new ResponseEntity<YearOfStudy>(YearsOfStudy, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<YearOfStudy> removeYearOfStudy(@PathVariable Long id) {
        try {
            yearOfStudyService.removeYearOfStudy(id);
        }catch (Exception e) {
            return new ResponseEntity<YearOfStudy>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<YearOfStudy>(HttpStatus.NO_CONTENT);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/subjects/{yearOfStudyId}", method=RequestMethod.GET)
    public ResponseEntity<ArrayList<Subject>> getYearsOfStudy(@PathVariable Long yearOfStudyId) {
        return new ResponseEntity<ArrayList<Subject>>(yearOfStudyService.getSubjects(yearOfStudyId), HttpStatus.OK);
    }

}
