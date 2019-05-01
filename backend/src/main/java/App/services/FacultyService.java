package App.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.Email;
import App.models.Faculty;
import App.models.FacultyEmails;
import App.models.FacultyPhones;
import App.models.Phone;
import App.models.StudyProgram;
import App.repositories.FacultyEmailsRepository;
import App.repositories.FacultyPhonesRepository;
import App.repositories.FacultyRepository;
import App.repositories.StudyProgramRepository;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepo;
    @Autowired
    private StudyProgramRepository studyProgramRepo;
    @Autowired
    private FacultyPhonesRepository facultyPhonesRepo;
    @Autowired
    private FacultyEmailsRepository facultyEmailsRepo;

    public FacultyService() {
    }

    public Iterable<Faculty> getFaculties() {
        return facultyRepo.findAll();
    }

    public Optional<Faculty> getFacultyById(Long id) {
        return facultyRepo.findById(id);
    }

    public void addFaculty(Faculty faculty) {
        facultyRepo.save(faculty);
    }

    public void removeFaculty(Long id) {
        Optional<Faculty> faculty = facultyRepo.findById(id);
        facultyRepo.delete(faculty.get());
    }

    public void updateFaculty(Long id, Faculty faculty) {
        Optional<Faculty> Fac = facultyRepo.findById(id);
        if(Fac.isPresent()) {
            faculty.setId(Fac.get().getId());
            facultyRepo.save(faculty);
        }
    }
    
    public ArrayList<StudyProgram> getStudyPrograms(Long facultyId) {
    	return studyProgramRepo.findByFacultyIdEquals(facultyId);
    }

    public ArrayList<Phone> getFacultyPhones(Long facultyId) {
    	ArrayList<FacultyPhones> phonesId = facultyPhonesRepo.findByFacultyIdEquals(facultyId);
    	ArrayList<Phone> phones = new ArrayList<Phone>();
    	for(FacultyPhones p : phonesId) {
    		phones.add(p.getPhone());
    	}
    	return phones;
    }
    
    public ArrayList<Email> getFacultyEmails(Long facultyId) {
    	ArrayList<FacultyEmails> emailsId = facultyEmailsRepo.findByFacultyIdEquals(facultyId);
    	ArrayList<Email> emails = new ArrayList<Email>();
    	for(FacultyEmails p : emailsId) {
    		emails.add(p.getEmail());
    	}
    	return emails;
    }
}
