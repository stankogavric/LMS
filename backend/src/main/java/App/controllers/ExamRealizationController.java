package App.controllers;

import java.util.ArrayList;
import java.util.Map;

import org.json.JSONObject;
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

import App.dto.StudentExamRegistrationDTO;
import App.models.ExamRealization;
import App.services.ExamRealizationService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/examrealization")
public class ExamRealizationController {
	
	@Autowired
	ExamRealizationService examRealService;
	
	@JsonView(HideOptionalProperties.class)
	@RequestMapping(value="/{studentId}", method=RequestMethod.GET)
	public ResponseEntity<ArrayList<ExamRealization>> getRegisteredExamsByStudent(@PathVariable Long studentId){
		ArrayList<ExamRealization> registeredExams = examRealService.getRegisteredExamsByStudent(studentId);
		if(registeredExams.size() == 0) return new ResponseEntity<ArrayList<ExamRealization>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<ArrayList<ExamRealization>>(registeredExams, HttpStatus.OK);
	}
	
	@JsonView(HideOptionalProperties.class)
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<HttpStatus> registerExamByStudent(@RequestBody Map json){
		try {
			JSONObject jsonObj=new JSONObject(json);
			if (examRealService.registerExam(jsonObj.getLong("examId"), jsonObj.getLong("subjectRealId"), jsonObj.getString("studentUsername")))
				return new ResponseEntity<>(HttpStatus.CREATED);
			else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(HideOptionalProperties.class)
	@RequestMapping(value="/{teacherUsername}/grading/{subjectId}", method=RequestMethod.GET)
	public ResponseEntity<ArrayList<StudentExamRegistrationDTO>> getRegisteredExamsOnSubject(@PathVariable String teacherUsername, @PathVariable Long subjectId){
		ArrayList<StudentExamRegistrationDTO> registeredExams = examRealService.getRegisteredExamsBySubject(subjectId, teacherUsername);
		if(registeredExams.size() == 0) return new ResponseEntity<ArrayList<StudentExamRegistrationDTO>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<ArrayList<StudentExamRegistrationDTO>>(registeredExams, HttpStatus.OK);
	}


}
