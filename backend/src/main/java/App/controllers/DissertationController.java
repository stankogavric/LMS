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

import App.services.DissertationService;
import App.utils.View.ShowDissertation;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/dissertation")
public class DissertationController {
	
	@Autowired
	DissertationService dissService;
	
	@JsonView(ShowDissertation.class)
    @RequestMapping(value="/{studentId}", method=RequestMethod.GET)
    public ResponseEntity<ArrayList<Object>> getDissertationsByStudentId(@PathVariable Long studentId) {
		ArrayList<Object> diss = dissService.findDissertationByStudentId(studentId);
		if (diss.size() != 0) return new ResponseEntity<ArrayList<Object>>(diss, HttpStatus.OK);
        return new ResponseEntity<ArrayList<Object>>(HttpStatus.NO_CONTENT);
    }

}
