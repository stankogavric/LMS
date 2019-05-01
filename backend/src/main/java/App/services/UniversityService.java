package App.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.Email;
import App.models.Phone;
import App.models.University;
import App.models.UniversityEmails;
import App.models.UniversityPhones;
import App.repositories.UniversityEmailsRepository;
import App.repositories.UniversityPhonesRepository;
import App.repositories.UniversityRepository;

@Service
public class UniversityService {

    @Autowired
    private UniversityRepository universityRepo;
    @Autowired
    private UniversityPhonesRepository universityPhonesRepo;
    @Autowired
    private UniversityEmailsRepository universityEmailsRepo;

    public UniversityService() {
    }

    public Iterable<University> getUniversities() {
        return universityRepo.findAll();
    }

    public Optional<University> getUniversityById(Long id) {
        return universityRepo.findById(id);
    }

    public void addUniversity(University university) {
        universityRepo.save(university);
    }

    public void removeUniversity(Long id) {
        Optional<University> university = universityRepo.findById(id);
        universityRepo.delete(university.get());
    }

    public void updateUniversity(Long id, University university) {
        Optional<University> Uni = universityRepo.findById(id);
        if(Uni.isPresent()) {
            university.setId(Uni.get().getId());
            universityRepo.save(university);
        }
    }
    
    public ArrayList<Phone> getUniversityPhones(Long universityId) {
    	ArrayList<UniversityPhones> phonesId = universityPhonesRepo.findByUniversityIdEquals(universityId);
    	ArrayList<Phone> phones = new ArrayList<Phone>();
    	for(UniversityPhones p : phonesId) {
    		phones.add(p.getPhone());
    	}
    	return phones;
    }
    
    public ArrayList<Email> getUniversityEmails(Long universityId) {
    	ArrayList<UniversityEmails> emailsId = universityEmailsRepo.findByUniversityIdEquals(universityId);
    	ArrayList<Email> emails = new ArrayList<Email>();
    	for(UniversityEmails p : emailsId) {
    		emails.add(p.getEmail());
    	}
    	return emails;
    }

}
