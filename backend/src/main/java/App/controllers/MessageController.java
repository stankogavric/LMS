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

import App.models.Message;
import App.services.MessageService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<Message>> getMessages() {
        return new ResponseEntity<Iterable<Message>>(messageService.getMessages(), HttpStatus.OK);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/user/{username}", method=RequestMethod.GET)
    public ResponseEntity<Iterable<Message>> getMessagesByUser(@PathVariable String username) {
        return new ResponseEntity<Iterable<Message>>(messageService.getMessagesByUser(username), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Message> getMessageById(@PathVariable Long id) {
        Optional<Message> message = messageService.getMessageById(id);
        if(message.isPresent()) {
            return new ResponseEntity<Message>(message.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Message>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<Message> addMessage(@RequestBody Message Messages) {
        messageService.addMessage(Messages);
        return new ResponseEntity<Message>(Messages, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Message> updateMessage(@PathVariable Long id, @RequestBody Message Messages) {
        messageService.updateMessage(id, Messages);
        return new ResponseEntity<Message>(Messages, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Message> removeMessage(@PathVariable Long id) {
        try {
            messageService.removeMessage(id);
        }catch (Exception e) {
            return new ResponseEntity<Message>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Message>(HttpStatus.NO_CONTENT);
    }

}

