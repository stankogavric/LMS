package App.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.Subject;
import App.models.Teacher;
import App.models.TeacherRealization;
import App.repositories.TeacherRealizationRepository;

@Service
public class TeacherRealizationService {

    @Autowired
    private TeacherRealizationRepository teacherRealizationRepo;

    public TeacherRealizationService() {
    }

    public Iterable<TeacherRealization> getTeacherRealizations() {
        return teacherRealizationRepo.findAll();
    }
    
    public Iterable<Optional<TeacherRealization>> getTeacherRealizationsByYearOfStudy(Long yearOfStudyId) {
        return teacherRealizationRepo.getByYearOfStudy(yearOfStudyId);
    }

    public Optional<TeacherRealization> getTeacherRealizationById(Long id) {
        return teacherRealizationRepo.findById(id);
    }

    public void addTeacherRealization(TeacherRealization teacherRealization) {
        teacherRealizationRepo.save(teacherRealization);
    }

    public void removeTeacherRealization(Long id) {
        Optional<TeacherRealization> teacherRealization = teacherRealizationRepo.findById(id);
        teacherRealizationRepo.delete(teacherRealization.get());
    }

    public void updateTeacherRealization(Long id, TeacherRealization teacherRealization) {
        Optional<TeacherRealization> Tea = teacherRealizationRepo.findById(id);
        if(Tea.isPresent()) {
            teacherRealization.setId(Tea.get().getId());
            teacherRealizationRepo.save(teacherRealization);
        }
    }
    
    public Iterable<Teacher> getTeachersWhoTeachSubject(Long subjectId) {
        return teacherRealizationRepo.findTeachersWhoTeachSubject(subjectId);
    }
    
    public Iterable<Subject> getTeacherSubjects(String username){
    	Date date = new Date();  
    	return teacherRealizationRepo.getTeacherSubjects(username, date);
    }

}
