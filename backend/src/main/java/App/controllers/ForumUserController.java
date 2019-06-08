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

import App.models.ForumUser;
import App.services.ForumUserService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/forumuser")
public class ForumUserController {

    @Autowired
    ForumUserService forumUserService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<ForumUser>> getForumUsers() {
        return new ResponseEntity<Iterable<ForumUser>>(forumUserService.getForumUsers(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<ForumUser> getForumUserById(@PathVariable Long id) {
        Optional<ForumUser> forumUser = forumUserService.getForumUserById(id);
        if(forumUser.isPresent()) {
            return new ResponseEntity<ForumUser>(forumUser.get(), HttpStatus.OK);
        }
        return new ResponseEntity<ForumUser>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<ForumUser> addForumUser(@RequestBody ForumUser ForumUsers) {
        forumUserService.addForumUser(ForumUsers);
        return new ResponseEntity<ForumUser>(ForumUsers, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<ForumUser> updateForumUser(@PathVariable Long id, @RequestBody ForumUser ForumUsers) {
        forumUserService.updateForumUser(id, ForumUsers);
        return new ResponseEntity<ForumUser>(ForumUsers, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<ForumUser> removeForumUser(@PathVariable Long id) {
        try {
            forumUserService.removeForumUser(id);
        }catch (Exception e) {
            return new ResponseEntity<ForumUser>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ForumUser>(HttpStatus.NO_CONTENT);
    }

}
