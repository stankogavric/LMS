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

import App.models.TeachingMaterial;
import App.services.TeachingMaterialService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/teachingmaterial")
public class TeachingMaterialController {

	@Autowired
    TeachingMaterialService teachingMaterialService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<TeachingMaterial>> getTeachingMaterials() {
        return new ResponseEntity<Iterable<TeachingMaterial>>(teachingMaterialService.getTeachingMaterials(), HttpStatus.OK);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/subject/{id}", method=RequestMethod.GET)
    public ResponseEntity<Iterable<Optional<TeachingMaterial>>> getTeachingMaterialsBySubject(@PathVariable Long id) {
        return new ResponseEntity<Iterable<Optional<TeachingMaterial>>>(teachingMaterialService.getTeachingMaterialsBySubject(id), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<TeachingMaterial> getTeachingMaterialById(@PathVariable Long id) {
        Optional<TeachingMaterial> teachingMaterial = teachingMaterialService.getTeachingMaterialById(id);
        if(teachingMaterial.isPresent()) {
            return new ResponseEntity<TeachingMaterial>(teachingMaterial.get(), HttpStatus.OK);
        }
        return new ResponseEntity<TeachingMaterial>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<TeachingMaterial> addTeachingMaterial(@RequestBody TeachingMaterial TeachingMaterials) {
        teachingMaterialService.addTeachingMaterial(TeachingMaterials);
        return new ResponseEntity<TeachingMaterial>(TeachingMaterials, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<TeachingMaterial> updateTeachingMaterial(@PathVariable Long id, @RequestBody TeachingMaterial TeachingMaterials) {
        teachingMaterialService.updateTeachingMaterial(id, TeachingMaterials);
        return new ResponseEntity<TeachingMaterial>(TeachingMaterials, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<TeachingMaterial> removeTeachingMaterial(@PathVariable Long id) {
        try {
            teachingMaterialService.removeTeachingMaterial(id);
        }catch (Exception e) {
            return new ResponseEntity<TeachingMaterial>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<TeachingMaterial>(HttpStatus.NO_CONTENT);
    }

}