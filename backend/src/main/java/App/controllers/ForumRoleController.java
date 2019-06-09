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

import App.models.ForumRole;
import App.services.ForumRoleService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/forumrole")
public class ForumRoleController {

    @Autowired
    ForumRoleService forumRoleService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<ForumRole>> getForumRoles() {
        return new ResponseEntity<Iterable<ForumRole>>(forumRoleService.getForumRoles(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<ForumRole> getForumRoleById(@PathVariable Long id) {
        Optional<ForumRole> forumRole = forumRoleService.getForumRoleById(id);
        if(forumRole.isPresent()) {
            return new ResponseEntity<ForumRole>(forumRole.get(), HttpStatus.OK);
        }
        return new ResponseEntity<ForumRole>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<ForumRole> addForumRole(@RequestBody ForumRole ForumRoles) {
        forumRoleService.addForumRole(ForumRoles);
        return new ResponseEntity<ForumRole>(ForumRoles, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<ForumRole> updateForumRole(@PathVariable Long id, @RequestBody ForumRole ForumRoles) {
        forumRoleService.updateForumRole(id, ForumRoles);
        return new ResponseEntity<ForumRole>(ForumRoles, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<ForumRole> removeForumRole(@PathVariable Long id) {
        try {
            forumRoleService.removeForumRole(id);
        }catch (Exception e) {
            return new ResponseEntity<ForumRole>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ForumRole>(HttpStatus.NO_CONTENT);
    }

}
