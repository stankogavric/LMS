package App.controllers;

import java.util.ArrayList;
import java.util.Collection;
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

import App.dto.StudentDTO;
import App.mapper.StudentMapper;
import App.models.Student;
import App.models.Subject;
import App.models.SubjectAttendance;
import App.services.SubjectAttendanceService;
import App.utils.View.HideOptionalProperties;
import App.utils.View.ShowStudent;
import App.utils.View.ShowYearOfStudy;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/subjectattendance")
public class SubjectAttendanceController {

    @Autowired
    SubjectAttendanceService subjectAttendanceService;
    @Autowired
    StudentMapper studentMapper;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<SubjectAttendance>> getSubjectAttendances() {
        return new ResponseEntity<Iterable<SubjectAttendance>>(subjectAttendanceService.getSubjectAttendances(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<SubjectAttendance> getSubjectAttendanceById(@PathVariable Long id) {
        Optional<SubjectAttendance> subjectAttendance = subjectAttendanceService.getSubjectAttendanceById(id);
        if(subjectAttendance.isPresent()) {
            return new ResponseEntity<SubjectAttendance>(subjectAttendance.get(), HttpStatus.OK);
        }
        return new ResponseEntity<SubjectAttendance>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<SubjectAttendance> addSubjectAttendance(@RequestBody SubjectAttendance SubjectAttendances) {
        subjectAttendanceService.addSubjectAttendance(SubjectAttendances);
        return new ResponseEntity<SubjectAttendance>(SubjectAttendances, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<SubjectAttendance> updateSubjectAttendance(@PathVariable Long id, @RequestBody SubjectAttendance SubjectAttendances) {
        subjectAttendanceService.updateSubjectAttendance(id, SubjectAttendances);
        return new ResponseEntity<SubjectAttendance>(SubjectAttendances, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<SubjectAttendance> removeSubjectAttendance(@PathVariable Long id) {
        try {
            subjectAttendanceService.removeSubjectAttendance(id);
        }catch (Exception e) {
            return new ResponseEntity<SubjectAttendance>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<SubjectAttendance>(HttpStatus.NO_CONTENT);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/averageMark/{studentId}", method=RequestMethod.GET)
    public ResponseEntity<Double> getAverageMark(@PathVariable Long studentId) {
        Double averageMark = subjectAttendanceService.getAverageMark(studentId);
        return new ResponseEntity<Double>(averageMark, HttpStatus.OK);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/studentsWhoDidntPassExam/{subjectId}", method=RequestMethod.GET)
    public ResponseEntity<Iterable<Student>> getStudentsWhoDidntPassExam(@PathVariable Long subjectId) {
        return new ResponseEntity<Iterable<Student>>(subjectAttendanceService.getStudentsWhoDidntPassExam(subjectId), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/subjects/{studentUsername}", method=RequestMethod.GET)
    public ResponseEntity<ArrayList<Subject>> getCurrentSubjectsByStudentId(@PathVariable String studentUsername) {
    	return new ResponseEntity<ArrayList<Subject>>(subjectAttendanceService.getCurrentSubjects(studentUsername), HttpStatus.OK);
    }
    
    @JsonView(ShowYearOfStudy.class)
    @RequestMapping(value="/pastSubjects/{studentUsername}", method=RequestMethod.GET)
    public ResponseEntity<ArrayList<Object>> getPastSubjectsByStudentUsername(@PathVariable String studentUsername) {
    	return new ResponseEntity<ArrayList<Object>>(subjectAttendanceService.getPastSubjects(studentUsername), HttpStatus.OK);
    }
    
    @JsonView(ShowStudent.class)
    @RequestMapping(value="/teacher/{teacherUsername}/{subjectId}/students", method=RequestMethod.GET)
    public ResponseEntity<Collection<StudentDTO>> getStudentsBySubject(@PathVariable String teacherUsername, @PathVariable Long subjectId){
    	Collection<Student> studenti = subjectAttendanceService.getStudentsBySubject(subjectId, teacherUsername);
    	
    	return new ResponseEntity<Collection<StudentDTO>>(studentMapper.toDtoList(studenti), HttpStatus.OK);
    }
    
}
