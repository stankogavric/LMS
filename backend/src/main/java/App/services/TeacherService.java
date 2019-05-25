package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.SubjectRealization;
import App.models.Teacher;
import App.repositories.SubjectRealizationRepository;
import App.repositories.TeacherRepository;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepo;
    @Autowired
    private SubjectRealizationRepository subjectRealizationRepo;
    
    @Autowired
    private LoginService loginServ;

    public TeacherService() {
    }

    public Iterable<Teacher> getTeachers() {
        return teacherRepo.findAll();
    }

    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepo.findById(id);
    }

    public void addTeacher(Teacher teacher) {
    	loginServ.addPermsion(teacher.getAccountData(), "ROLE_TEACHER");
        teacherRepo.save(teacher);
    }

    public void removeTeacher(Long id) {
        Optional<Teacher> teacher = teacherRepo.findById(id);
        Teacher t = teacher.get();
        t.setDeleted(true);
        teacherRepo.save(t);
    }

    public void updateTeacher(Long id, Teacher teacher) {
        Optional<Teacher> Tea = teacherRepo.findById(id);
        if(Tea.isPresent()) {
            teacher.setId(Tea.get().getId());
            teacherRepo.save(teacher);
        }
    }
    
    public Iterable<Optional<Teacher>> getTeachersByFirstName(String firstName){
    	return teacherRepo.findTeachersByFirstName("%"+firstName+"%");
    }
    
    public Optional<Teacher> getTeacherByJmbg(String jmbg){
    	return teacherRepo.findTeacherByJmbg(jmbg);
    }
    
    public Iterable<SubjectRealization> getSubjectRealizations(String username) {
        return subjectRealizationRepo.findByTeacherUsername(username);
    }

}
