package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.Subject;
import App.repositories.SubjectRepository;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepo;

    public SubjectService() {
    }

    public Iterable<Subject> getSubjects() {
        return subjectRepo.findAll();
    }

    public Optional<Subject> getSubjectById(Long id) {
        return subjectRepo.findById(id);
    }

    public void addSubject(Subject subject) {
        subjectRepo.save(subject);
    }

    public void removeSubject(Long id) {
        Optional<Subject> subject = subjectRepo.findById(id);
        subjectRepo.delete(subject.get());
    }

    public void updateSubject(Long id, Subject subject) {
        Optional<Subject> Sub = subjectRepo.findById(id);
        if(Sub.isPresent()) {
            subject.setId(Sub.get().getId());
            subjectRepo.save(subject);
        }
    }
    
    public Iterable<Optional<Subject>> getSubjectsByName(String name){
    	return subjectRepo.findByNameLike("%"+name+"%");
    }

}
