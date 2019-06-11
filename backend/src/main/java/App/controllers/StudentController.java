package App.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;

import App.dto.StudentDTO;
import App.dto.StudentDetailsDTO;
import App.mapper.StudentDetailsMapper;
import App.mapper.StudentMapper;
import App.models.Student;
import App.services.FileService;
import App.services.StudentService;
import App.utils.GeneratePDF;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;
    
    @Autowired
    FileService fileService;
    
    @Autowired
    StudentMapper studentMapper;
    
    @Autowired
    StudentDetailsMapper studentDetailsMapper;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<Student>> getStudents() {
        return new ResponseEntity<Iterable<Student>>(studentService.getStudents(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/pdf", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> exportStudentsToPDF() {

        ByteArrayInputStream bis = GeneratePDF.students((List<Student>)studentService.getStudents());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=students.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        if(student.isPresent()) {
            return new ResponseEntity<Student>(student.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/username/{username}", method=RequestMethod.GET)
    public ResponseEntity<Student> getStudentByUsername(@PathVariable String username) {
        Optional<Student> student = studentService.getStudentByUsername(username);
        if(student.isPresent()) {
            return new ResponseEntity<Student>(student.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/{username}", method=RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Student> updateStudent(@PathVariable String username, @RequestPart("profileImage") Optional<MultipartFile> file, @RequestPart("data") String studentStr) throws IOException {
    	Student student = new ObjectMapper().readValue(studentStr, Student.class);
    	if(file.isPresent()) {
			fileService.saveProfileImage(file.get(), "student_" + student.getAccountData().getUsername(), student.getPersonalData());
		}
    	studentService.updateStudent(username, student);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Student> removeStudent(@PathVariable Long id) {
        try {
            studentService.removeStudent(id);
        }catch (Exception e) {
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/findByName/{name}", method=RequestMethod.GET)
    public ResponseEntity<Iterable<Optional<Student>>> getStudentsByFirstName(@PathVariable String firstName) {
        Iterable<Optional<Student>> students = studentService.getStudentsByFirstName(firstName);
        return new ResponseEntity<Iterable<Optional<Student>>>(students, HttpStatus.OK);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/findByJmbg/{jmbg}", method=RequestMethod.GET)
    public ResponseEntity<Student> getStudentByJmbg(@PathVariable String jmbg) {
        Optional<Student> student = studentService.getStudentByJmbg(jmbg);
        if(student.isPresent()) {
            return new ResponseEntity<Student>(student.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
    }
    
    @JsonView(HideOptionalProperties.class)
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATIVE_STAFF','ROLE_ADMINISTRATOR')")
	public ResponseEntity<Student> addStudent(@RequestPart("profileImage") Optional<MultipartFile> file, @RequestPart("data") String studentStr) throws IOException {
		Student student = new ObjectMapper().readValue(studentStr, Student.class);
		if(file.isPresent()) {
			fileService.saveProfileImage(file.get(), "student_" + student.getAccountData().getUsername(), student.getPersonalData());
		}
		studentService.addStudent(student);
		return new ResponseEntity<Student>(student, HttpStatus.CREATED);
	}

	@JsonView(HideOptionalProperties.class)
	@RequestMapping(value = "/search/", method = RequestMethod.GET)
	public ResponseEntity<Collection<StudentDTO>> searchStudents(@RequestParam(required = false) String firstName,
			@RequestParam(required = false) String lastName, @RequestParam(required = false) String indexNum,
			@RequestParam(required = false) String enrolment, @RequestParam(required = false) String avgGrade) {

		Collection<Student> students = studentService.searchStudents(firstName, lastName, indexNum, enrolment, avgGrade);
		if(students.size()>0) {
			Collection<StudentDTO> foundStudents = studentMapper.toDtoList(students);
			return new ResponseEntity<Collection<StudentDTO>>(foundStudents, HttpStatus.OK);
		}
		else return new ResponseEntity<Collection<StudentDTO>>(HttpStatus.NO_CONTENT);

	}
	
	@JsonView(HideOptionalProperties.class)
	@RequestMapping(value="/details/{id}", method=RequestMethod.GET)
	public ResponseEntity<StudentDetailsDTO> getStudentDetailsById(@PathVariable Long id){
		Optional<Student> student = studentService.getStudentById(id);
		System.out.println(student.get().getPersonalData().getFirstName());
		if (student.isPresent()) return new ResponseEntity<StudentDetailsDTO>(studentDetailsMapper.toDTO(student.get()), HttpStatus.OK);
		return new ResponseEntity<StudentDetailsDTO>(HttpStatus.NO_CONTENT);
		
	}

}
