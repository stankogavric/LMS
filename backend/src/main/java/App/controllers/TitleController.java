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

import App.models.Title;
import App.services.TitleService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/title")
public class TitleController {

    @Autowired
    TitleService titleService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<Title>> getTitles() {
        return new ResponseEntity<Iterable<Title>>(titleService.getTitles(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Title> getTitleById(@PathVariable Long id) {
        Optional<Title> title = titleService.getTitleById(id);
        if(title.isPresent()) {
            return new ResponseEntity<Title>(title.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Title>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<Title> addTitle(@RequestBody Title Titles) {
        titleService.addTitle(Titles);
        return new ResponseEntity<Title>(Titles, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Title> updateTitle(@PathVariable Long id, @RequestBody Title Titles) {
        titleService.updateTitle(id, Titles);
        return new ResponseEntity<Title>(Titles, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Title> removeTitle(@PathVariable Long id) {
        try {
            titleService.removeTitle(id);
        }catch (Exception e) {
            return new ResponseEntity<Title>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Title>(HttpStatus.NO_CONTENT);
    }

}
