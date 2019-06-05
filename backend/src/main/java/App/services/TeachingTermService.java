package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.TeachingTerm;
import App.repositories.TeachingTermRepository;

@Service
public class TeachingTermService {

	@Autowired
    private TeachingTermRepository teachingTermRepo;

    public TeachingTermService() {
    }

    public Iterable<TeachingTerm> getTeachingTerms() {
        return teachingTermRepo.findAll();
    }
    
    public Iterable<Optional<TeachingTerm>> getTeachingTermsByYearOfStudy(Long yearOfStudyId) {
        return teachingTermRepo.getByYearOfStudy(yearOfStudyId);
    }

    public Optional<TeachingTerm> getTeachingTermById(Long id) {
        return teachingTermRepo.findById(id);
    }

    public void addTeachingTerm(TeachingTerm teachingTerm) {
        teachingTermRepo.save(teachingTerm);
    }
    
    public void removeTeachingTerm(Long id) {
        Optional<TeachingTerm> teachingTerm = teachingTermRepo.findById(id);
        TeachingTerm t = teachingTerm.get();
        t.setDeleted(true);
        teachingTermRepo.save(t);
    }

    public void updateTeachingTerm(Long id, TeachingTerm teachingTerm) {
        Optional<TeachingTerm> Tea = teachingTermRepo.findById(id);
        if(Tea.isPresent()) {
            teachingTerm.setId(Tea.get().getId());
            teachingTermRepo.save(teachingTerm);
        }
    }

}
