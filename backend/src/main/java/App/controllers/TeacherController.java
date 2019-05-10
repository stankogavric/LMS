package App.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;

import App.models.Teacher;
import App.services.TeacherService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<Teacher>> getTeachers() {
        return new ResponseEntity<Iterable<Teacher>>(teacherService.getTeachers(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        Optional<Teacher> teacher = teacherService.getTeacherById(id);
        if(teacher.isPresent()) {
            return new ResponseEntity<Teacher>(teacher.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Teacher>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher Teachers) {
        teacherService.addTeacher(Teachers);
        return new ResponseEntity<Teacher>(Teachers, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody Teacher Teachers) {
        teacherService.updateTeacher(id, Teachers);
        return new ResponseEntity<Teacher>(Teachers, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Teacher> removeTeacher(@PathVariable Long id) {
        try {
            teacherService.removeTeacher(id);
        }catch (Exception e) {
            return new ResponseEntity<Teacher>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Teacher>(HttpStatus.NO_CONTENT);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/findByName/{name}", method=RequestMethod.GET)
    public ResponseEntity<Iterable<Optional<Teacher>>> getTeachersByFirstName(@PathVariable String firstName) {
        Iterable<Optional<Teacher>> teachers = teacherService.getTeachersByFirstName(firstName);
        return new ResponseEntity<Iterable<Optional<Teacher>>>(teachers, HttpStatus.OK);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/findByJmbg/{jmbg}", method=RequestMethod.GET)
    public ResponseEntity<Teacher> getTeacherByJmbg(@PathVariable String jmbg) {
        Optional<Teacher> teacher = teacherService.getTeacherByJmbg(jmbg);
        if(teacher.isPresent()) {
            return new ResponseEntity<Teacher>(teacher.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Teacher>(HttpStatus.NOT_FOUND);
    }
    
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Secured("ROLE_ADMINISTRATOR")
	public ResponseEntity<Teacher> uploadFile(@RequestPart("profileImage") MultipartFile file, @RequestPart("data") String teacherStr) throws IOException {
		Teacher teacher = new ObjectMapper().readValue(teacherStr, Teacher.class);
		File convertFile = new File("resources\\images\\profile images\\teacher_" + teacher.getAccountData().getUsername() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
		convertFile.createNewFile();
		FileOutputStream fout = new FileOutputStream(convertFile);
		fout.write(file.getBytes());
		fout.close();
		teacher.getPersonalData().setProfilePicturePath(convertFile.getPath());
		teacherService.addTeacher(teacher);
		return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
	}

}
