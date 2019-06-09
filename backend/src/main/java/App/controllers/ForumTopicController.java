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

import App.models.ForumTopic;
import App.services.ForumTopicService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/forumtopic")
public class ForumTopicController {

    @Autowired
    ForumTopicService forumTopicService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<ForumTopic>> getForumTopics() {
        return new ResponseEntity<Iterable<ForumTopic>>(forumTopicService.getForumTopics(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<ForumTopic> getForumTopicById(@PathVariable Long id) {
        Optional<ForumTopic> forumTopic = forumTopicService.getForumTopicById(id);
        if(forumTopic.isPresent()) {
            return new ResponseEntity<ForumTopic>(forumTopic.get(), HttpStatus.OK);
        }
        return new ResponseEntity<ForumTopic>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<ForumTopic> addForumTopic(@RequestBody ForumTopic ForumTopics) {
        forumTopicService.addForumTopic(ForumTopics);
        return new ResponseEntity<ForumTopic>(ForumTopics, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<ForumTopic> updateForumTopic(@PathVariable Long id, @RequestBody ForumTopic ForumTopics) {
        forumTopicService.updateForumTopic(id, ForumTopics);
        return new ResponseEntity<ForumTopic>(ForumTopics, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<ForumTopic> removeForumTopic(@PathVariable Long id) {
        try {
            forumTopicService.removeForumTopic(id);
        }catch (Exception e) {
            return new ResponseEntity<ForumTopic>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ForumTopic>(HttpStatus.NO_CONTENT);
    }

}
