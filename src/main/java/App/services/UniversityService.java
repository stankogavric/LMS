package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.University;
import App.repositories.UniversityRepository;

@Service
public class UniversityService {

    @Autowired
    private UniversityRepository universityRepo;

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

}
