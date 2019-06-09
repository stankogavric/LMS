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

import App.models.Post;
import App.services.PostService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<Post>> getPosts() {
        return new ResponseEntity<Iterable<Post>>(postService.getPosts(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Optional<Post> post = postService.getPostById(id);
        if(post.isPresent()) {
            return new ResponseEntity<Post>(post.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<Post> addPost(@RequestBody Post Posts) {
        postService.addPost(Posts);
        return new ResponseEntity<Post>(Posts, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post Posts) {
        postService.updatePost(id, Posts);
        return new ResponseEntity<Post>(Posts, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Post> removePost(@PathVariable Long id) {
        try {
            postService.removePost(id);
        }catch (Exception e) {
            return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Post>(HttpStatus.NO_CONTENT);
    }

}
