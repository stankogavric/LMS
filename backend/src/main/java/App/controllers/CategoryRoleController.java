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

import App.models.CategoryRole;
import App.services.CategoryRoleService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/categoryrole")
public class CategoryRoleController {

    @Autowired
    CategoryRoleService categoryRoleService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<CategoryRole>> getCategoryRoles() {
        return new ResponseEntity<Iterable<CategoryRole>>(categoryRoleService.getCategoryRoles(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<CategoryRole> getCategoryRoleById(@PathVariable Long id) {
        Optional<CategoryRole> categoryRole = categoryRoleService.getCategoryRoleById(id);
        if(categoryRole.isPresent()) {
            return new ResponseEntity<CategoryRole>(categoryRole.get(), HttpStatus.OK);
        }
        return new ResponseEntity<CategoryRole>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<CategoryRole> addCategoryRole(@RequestBody CategoryRole CategoryRoles) {
        categoryRoleService.addCategoryRole(CategoryRoles);
        return new ResponseEntity<CategoryRole>(CategoryRoles, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<CategoryRole> updateCategoryRole(@PathVariable Long id, @RequestBody CategoryRole CategoryRoles) {
        categoryRoleService.updateCategoryRole(id, CategoryRoles);
        return new ResponseEntity<CategoryRole>(CategoryRoles, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<CategoryRole> removeCategoryRole(@PathVariable Long id) {
        try {
            categoryRoleService.removeCategoryRole(id);
        }catch (Exception e) {
            return new ResponseEntity<CategoryRole>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<CategoryRole>(HttpStatus.NO_CONTENT);
    }

}
