package App.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;

import App.models.Topic;
import App.services.FileService;
import App.services.TopicService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    TopicService topicService;
    @Autowired
    FileService fileService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<Topic>> getTopics() {
        return new ResponseEntity<Iterable<Topic>>(topicService.getTopics(), HttpStatus.OK);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/bySubject/{id}", method=RequestMethod.GET)
    public ResponseEntity<Iterable<Optional<Topic>>> getTopicsBySubjectId(@PathVariable Long id) {
        return new ResponseEntity<Iterable<Optional<Topic>>>(topicService.getTopicsBySubjectId(id), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Topic> getTopicById(@PathVariable Long id) {
        Optional<Topic> topic = topicService.getTopicById(id);
        if(topic.isPresent()) {
            return new ResponseEntity<Topic>(topic.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Topic>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Topic> addTopic(@RequestPart("icon") Optional<MultipartFile> file, @RequestPart("data") String topicStr) throws IOException {
    	Topic topic = new ObjectMapper().readValue(topicStr, Topic.class);
    	Topic savedTopic = topicService.addTopic(topic);
    	if(file.isPresent()) {
    		fileService.saveTopicIcon(file.get(), "topic_" + savedTopic.getId(), savedTopic);
    		topicService.updateTopic(savedTopic.getId(), savedTopic);
    	}
        return new ResponseEntity<Topic>(savedTopic, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @RequestBody Topic Topics) {
        topicService.updateTopic(id, Topics);
        return new ResponseEntity<Topic>(Topics, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Topic> removeTopic(@PathVariable Long id) {
        try {
            topicService.removeTopic(id);
        }catch (Exception e) {
            return new ResponseEntity<Topic>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Topic>(HttpStatus.NO_CONTENT);
    }

}
