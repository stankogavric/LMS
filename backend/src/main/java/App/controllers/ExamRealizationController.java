package App.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

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
	

}
