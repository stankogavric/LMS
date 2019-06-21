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

import App.dto.ExamDTO;
import App.dto.ExamRegistrationDTO;
import App.dto.StudentExamRegistrationDTO;
import App.models.Exam;
import App.models.ExamType;
import App.services.ExamService;
import App.utils.View;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/exam")
public class ExamController {
	
	@Autowired
    ExamService examService;
	
	@JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<Exam>> getExams() {
        return new ResponseEntity<Iterable<Exam>>(examService.getExams(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Exam> getExamById(@PathVariable Long id) {
        Optional<Exam> exam = examService.getExamById(id);
        if(exam.isPresent()) {
            return new ResponseEntity<Exam>(exam.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Exam>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<Exam> addExam(@RequestBody Exam Exams) {
    	examService.addExam(Exams);
        return new ResponseEntity<Exam>(Exams, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Exam> updateExam(@PathVariable Long id, @RequestBody Exam Exams) {
    	examService.updateExam(id, Exams);
        return new ResponseEntity<Exam>(Exams, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Exam> removeExam(@PathVariable Long id) {
        try {
        	examService.removeExam(id);
        }catch (Exception e) {
            return new ResponseEntity<Exam>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Exam>(HttpStatus.NO_CONTENT);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/types", method=RequestMethod.GET)
    public ResponseEntity<Iterable<ExamType>> getExamTypes() {
        return new ResponseEntity<Iterable<ExamType>>(examService.getExamTypes(), HttpStatus.OK);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{studentId}/exams", method=RequestMethod.GET)
    public ResponseEntity<Collection<ExamDTO>> getExamsByStudent(@PathVariable Long studentId){
    	Collection<ExamDTO> exams = examService.getExamsByStudent(studentId);
    	if (exams.isEmpty()) return new ResponseEntity<Collection<ExamDTO>>(HttpStatus.NO_CONTENT);
    	return new ResponseEntity<Collection<ExamDTO>>(exams, HttpStatus.OK);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/availableExams/{username}", method=RequestMethod.GET)
    public ResponseEntity<ArrayList<ExamRegistrationDTO>> getAvailableExamsForRegistration(@PathVariable String username) {
    	ArrayList<ExamRegistrationDTO> exams = examService.getAvailableExamsForRegistration(username);
    	if (exams.size() == 0) return new ResponseEntity<ArrayList<ExamRegistrationDTO>>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<ArrayList<ExamRegistrationDTO>>(exams, HttpStatus.OK);
    }
    
    @JsonView(View.HideOptionalProperties.class)
    @RequestMapping(value="/{teacherUsername}/{subjectId}/addGrades", method=RequestMethod.POST)
    public ResponseEntity<ArrayList<Exam>> addGrades(@PathVariable String teacherUsername, @PathVariable Long subjectId, @ RequestBody ArrayList<StudentExamRegistrationDTO> studentGrades){
    	
    	try {
			examService.addGrades(subjectId, teacherUsername, studentGrades);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
    }
    
}
