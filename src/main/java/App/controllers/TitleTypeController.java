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

import App.models.TitleType;
import App.services.TitleTypeService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/titletype")
public class TitleTypeController {

    @Autowired
    TitleTypeService titleTypeService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<TitleType>> getTitleTypes() {
        return new ResponseEntity<Iterable<TitleType>>(titleTypeService.getTitleTypes(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<TitleType> getTitleTypeById(@PathVariable Long id) {
        Optional<TitleType> titleType = titleTypeService.getTitleTypeById(id);
        if(titleType.isPresent()) {
            return new ResponseEntity<TitleType>(titleType.get(), HttpStatus.OK);
        }
        return new ResponseEntity<TitleType>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<TitleType> addTitleType(@RequestBody TitleType TitleTypes) {
        titleTypeService.addTitleType(TitleTypes);
        return new ResponseEntity<TitleType>(TitleTypes, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<TitleType> updateTitleType(@PathVariable Long id, @RequestBody TitleType TitleTypes) {
        titleTypeService.updateTitleType(id, TitleTypes);
        return new ResponseEntity<TitleType>(TitleTypes, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<TitleType> removeTitleType(@PathVariable Long id) {
        try {
            titleTypeService.removeTitleType(id);
        }catch (Exception e) {
            return new ResponseEntity<TitleType>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<TitleType>(HttpStatus.NO_CONTENT);
    }

}
