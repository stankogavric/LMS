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

import App.models.ForumUserForumRole;
import App.services.ForumUserForumRoleService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/forumuserforumrole")
public class ForumUserForumRoleController {

    @Autowired
    ForumUserForumRoleService forumUserForumRoleService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<ForumUserForumRole>> getForumUserForumRoles() {
        return new ResponseEntity<Iterable<ForumUserForumRole>>(forumUserForumRoleService.getForumUserForumRoles(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<ForumUserForumRole> getForumUserForumRoleById(@PathVariable Long id) {
        Optional<ForumUserForumRole> forumUserForumRole = forumUserForumRoleService.getForumUserForumRoleById(id);
        if(forumUserForumRole.isPresent()) {
            return new ResponseEntity<ForumUserForumRole>(forumUserForumRole.get(), HttpStatus.OK);
        }
        return new ResponseEntity<ForumUserForumRole>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<ForumUserForumRole> addForumUserForumRole(@RequestBody ForumUserForumRole ForumUserForumRoles) {
        forumUserForumRoleService.addForumUserForumRole(ForumUserForumRoles);
        return new ResponseEntity<ForumUserForumRole>(ForumUserForumRoles, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<ForumUserForumRole> updateForumUserForumRole(@PathVariable Long id, @RequestBody ForumUserForumRole ForumUserForumRoles) {
        forumUserForumRoleService.updateForumUserForumRole(id, ForumUserForumRoles);
        return new ResponseEntity<ForumUserForumRole>(ForumUserForumRoles, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<ForumUserForumRole> removeForumUserForumRole(@PathVariable Long id) {
        try {
            forumUserForumRoleService.removeForumUserForumRole(id);
        }catch (Exception e) {
            return new ResponseEntity<ForumUserForumRole>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ForumUserForumRole>(HttpStatus.NO_CONTENT);
    }

}
