package App.controllers;

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

import App.models.AdministrativeStaff;
import App.models.Student;
import App.models.YearOfStudy;
import App.services.AdministrativeStaffService;
import App.services.FileService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/administrativestaff")
public class AdministrativeStaffController {

    @Autowired
    AdministrativeStaffService administrativeStaffService;

    @Autowired
    FileService fileService;
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<AdministrativeStaff>> getAdministrativeStaff() {
        return new ResponseEntity<Iterable<AdministrativeStaff>>(administrativeStaffService.getAdministrativeStaff(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<AdministrativeStaff> getAdministrativeStaffById(@PathVariable Long id) {
        Optional<AdministrativeStaff> administrativeStaff = administrativeStaffService.getAdministrativeStaffById(id);
        if(administrativeStaff.isPresent()) {
            return new ResponseEntity<AdministrativeStaff>(administrativeStaff.get(), HttpStatus.OK);
        }
        return new ResponseEntity<AdministrativeStaff>(HttpStatus.NOT_FOUND);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/username/{username}", method=RequestMethod.GET)
    public ResponseEntity<AdministrativeStaff> getAdministrativeStaffByUsername(@PathVariable String username) {
        Optional<AdministrativeStaff> administrativeStaff = administrativeStaffService.getAdministrativeStaffByUsername(username);
        if(administrativeStaff.isPresent()) {
            return new ResponseEntity<AdministrativeStaff>(administrativeStaff.get(), HttpStatus.OK);
        }
        return new ResponseEntity<AdministrativeStaff>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/{username}", method=RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdministrativeStaff> updateAdministrativeStaff(@PathVariable String username, @RequestPart("profileImage") Optional<MultipartFile> file, @RequestPart("data") String admStfStr) throws IOException {
    	AdministrativeStaff admStf = new ObjectMapper().readValue(admStfStr, AdministrativeStaff.class);
		if(file.isPresent()) {
			fileService.saveProfileImage(file.get(), "administrative_staff_" + admStf.getAccountData().getUsername(), admStf.getPersonalData());
		}
    	administrativeStaffService.updateAdministrativeStaff(username, admStf);
        return new ResponseEntity<AdministrativeStaff>(admStf, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<AdministrativeStaff> removeAdministrativeStaff(@PathVariable Long id) {
        try {
            administrativeStaffService.removeAdministrativeStaff(id);
        }catch (Exception e) {
            return new ResponseEntity<AdministrativeStaff>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<AdministrativeStaff>(HttpStatus.NO_CONTENT);
    }
    
    @JsonView(HideOptionalProperties.class)
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Secured("ROLE_ADMINISTRATOR")
	public ResponseEntity<AdministrativeStaff> addAdministrativeStaff(@RequestPart("profileImage") Optional<MultipartFile> file, @RequestPart("data") String admStfStr) throws IOException {
		AdministrativeStaff admStf = new ObjectMapper().readValue(admStfStr, AdministrativeStaff.class);
		if(file.isPresent()) {
			fileService.saveProfileImage(file.get(), "administrative_staff_" + admStf.getAccountData().getUsername(), admStf.getPersonalData());
		}
		administrativeStaffService.addAdministrativeStaff(admStf);
		return new ResponseEntity<AdministrativeStaff>(admStf, HttpStatus.CREATED);
	}
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/enrollment/{yearOfStudyId}", method=RequestMethod.GET)
    public ResponseEntity<Iterable<Student>> getStudentsForEnrollmentToTheNextYear(@PathVariable Long yearOfStudyId) {
        return new ResponseEntity<Iterable<Student>>(administrativeStaffService.getStudentsForEnrollmentToTheNextYear(yearOfStudyId), HttpStatus.OK);
    }
    
//    @JsonView(HideOptionalProperties.class)
//    @RequestMapping(value="/enrollment/{studyProgram}/{yearOfStudy}", method=RequestMethod.GET)
//    public ResponseEntity<Iterable<Student>> getStudentsForEnrollmentToTheNextYear(@PathVariable String studyProgram, @PathVariable int yearOfStudy) {
//        return new ResponseEntity<Iterable<Student>>(administrativeStaffService.getStudentsForEnrollmentToTheNextYear(studyProgram, yearOfStudy), HttpStatus.OK);
//    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/enrollment/{studentId}", method=RequestMethod.PUT)
    public ResponseEntity<Boolean> enrollmentStudentToTheNextYear(@PathVariable Long studentId, @RequestBody YearOfStudy yearOfStudy) {
    	return new ResponseEntity<Boolean>(administrativeStaffService.enrollmentStudentToTheNextYear(studentId, yearOfStudy), HttpStatus.OK);
    }
    
//    @JsonView(HideOptionalProperties.class)
//    @RequestMapping(value="/enrollment", method=RequestMethod.POST)
//    public ResponseEntity<Boolean> enrollmentStudentToTheNextYear(@RequestBody ArrayList<String> ids) {
//        return new ResponseEntity<Boolean>(administrativeStaffService.enrollmentStudentToTheNextYear(ids), HttpStatus.OK);
//    }

}
