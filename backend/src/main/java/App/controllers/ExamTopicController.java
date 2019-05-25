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

import App.models.ExamTopic;
import App.services.ExamTopicService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/examTopic")
public class ExamTopicController {
	@Autowired
    ExamTopicService examTopicService;
	
	@JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<ExamTopic>> getExamTopics() {
        return new ResponseEntity<Iterable<ExamTopic>>(examTopicService.getExamTopics(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<ExamTopic> getExamTopicById(@PathVariable Long id) {
        Optional<ExamTopic> examTopic = examTopicService.getExamTopicById(id);
        if(examTopic.isPresent()) {
            return new ResponseEntity<ExamTopic>(examTopic.get(), HttpStatus.OK);
        }
        return new ResponseEntity<ExamTopic>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<ExamTopic> addExamTopic(@RequestBody ExamTopic examTopic) {
    	examTopicService.addExamTopic(examTopic);
        return new ResponseEntity<ExamTopic>(examTopic, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<ExamTopic> updateExamTopic(@PathVariable Long id, @RequestBody ExamTopic examTopic) {
    	examTopicService.updateExamTopic(id, examTopic);
        return new ResponseEntity<ExamTopic>(examTopic, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<ExamTopic> removeExamTopic(@PathVariable Long id) {
        try {
        	examTopicService.removeExamTopic(id);
        }catch (Exception e) {
            return new ResponseEntity<ExamTopic>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ExamTopic>(HttpStatus.NO_CONTENT);
    }
}
