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

import App.models.PostFile;
import App.services.PostFileService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/postfile")
public class PostFileController {

    @Autowired
    PostFileService postFileService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<PostFile>> getPostFiles() {
        return new ResponseEntity<Iterable<PostFile>>(postFileService.getPostFiles(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<PostFile> getPostFileById(@PathVariable Long id) {
        Optional<PostFile> postFile = postFileService.getPostFileById(id);
        if(postFile.isPresent()) {
            return new ResponseEntity<PostFile>(postFile.get(), HttpStatus.OK);
        }
        return new ResponseEntity<PostFile>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<PostFile> addPostFile(@RequestBody PostFile PostFiles) {
        postFileService.addPostFile(PostFiles);
        return new ResponseEntity<PostFile>(PostFiles, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<PostFile> updatePostFile(@PathVariable Long id, @RequestBody PostFile PostFiles) {
        postFileService.updatePostFile(id, PostFiles);
        return new ResponseEntity<PostFile>(PostFiles, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<PostFile> removePostFile(@PathVariable Long id) {
        try {
            postFileService.removePostFile(id);
        }catch (Exception e) {
            return new ResponseEntity<PostFile>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<PostFile>(HttpStatus.NO_CONTENT);
    }

}
