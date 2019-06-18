package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.SubjectRealization;
import App.models.Teacher;
import App.repositories.SubjectRealizationRepository;

@Service
public class SubjectRealizationService {

    @Autowired
    private SubjectRealizationRepository subjectRealizationRepo;

    public SubjectRealizationService() {
    }

    public Iterable<SubjectRealization> getSubjectRealization() {
        return subjectRealizationRepo.findAll();
    }
    
    public Iterable<SubjectRealization> getSubjectRealizationByYearOfStudyId(Long id) {
        return subjectRealizationRepo.findByYearOfStudyId(id);
    }

    public Optional<SubjectRealization> getSubjectRealizationById(Long id) {
        return subjectRealizationRepo.findById(id);
    }

    public void addSubjectRealization(SubjectRealization subjectRealization) {
        subjectRealizationRepo.save(subjectRealization);
    }

    public void removeSubjectRealization(Long id) {
        Optional<SubjectRealization> subjectRealization = subjectRealizationRepo.findById(id);
        subjectRealizationRepo.delete(subjectRealization.get());
    }

    public void updateSubjectRealization(Long id, SubjectRealization subjectRealization) {
        Optional<SubjectRealization> Sub = subjectRealizationRepo.findById(id);
        if(Sub.isPresent()) {
            subjectRealization.setId(Sub.get().getId());
            subjectRealizationRepo.save(subjectRealization);
        }
    }
    
    public Iterable<Teacher> getTeachersWhoTeachExercises(Long subjectId) {
        return subjectRealizationRepo.findTeachersWhoTeachExercises(subjectId);
    }

}
