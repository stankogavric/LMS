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

import App.models.StudyProgram;
import App.models.Subject;
import App.models.YearOfStudy;
import App.services.StudyProgramService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/studyprogram")
public class StudyProgramController {

    @Autowired
    StudyProgramService studyProgramService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<StudyProgram>> getStudyPrograms() {
        return new ResponseEntity<Iterable<StudyProgram>>(studyProgramService.getStudyPrograms(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<StudyProgram> getStudyProgramById(@PathVariable Long id) {
        Optional<StudyProgram> studyProgram = studyProgramService.getStudyProgramById(id);
        if(studyProgram.isPresent()) {
            return new ResponseEntity<StudyProgram>(studyProgram.get(), HttpStatus.OK);
        }
        return new ResponseEntity<StudyProgram>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<StudyProgram> addStudyProgram(@RequestBody StudyProgram StudyPrograms) {
        studyProgramService.addStudyProgram(StudyPrograms);
        return new ResponseEntity<StudyProgram>(StudyPrograms, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<StudyProgram> updateStudyProgram(@PathVariable Long id, @RequestBody StudyProgram StudyPrograms) {
        studyProgramService.updateStudyProgram(id, StudyPrograms);
        return new ResponseEntity<StudyProgram>(StudyPrograms, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<StudyProgram> removeStudyProgram(@PathVariable Long id) {
        try {
            studyProgramService.removeStudyProgram(id);
        }catch (Exception e) {
            return new ResponseEntity<StudyProgram>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<StudyProgram>(HttpStatus.NO_CONTENT);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/studyProgramsCandidatedForRemoval/{studyProgram}", method=RequestMethod.GET)
    public ResponseEntity<Iterable<Subject>> getSubjectsCandidatedForRemoval(@PathVariable Long studyProgramId) {
        return new ResponseEntity<Iterable<Subject>>(studyProgramService.getSubjectsCandidatedForRemoval(studyProgramId), HttpStatus.OK);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/yearsofstudy/{studyProgramId}", method=RequestMethod.GET)
    public ResponseEntity<ArrayList<YearOfStudy>> getYearsOfStudy(@PathVariable Long studyProgramId) {
        return new ResponseEntity<ArrayList<YearOfStudy>>(studyProgramService.getYearsOfStudy(studyProgramId), HttpStatus.OK);
    }

}
