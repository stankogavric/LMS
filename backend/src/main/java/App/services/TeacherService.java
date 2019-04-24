package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.Teacher;
import App.repositories.TeacherRepository;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepo;

    public TeacherService() {
    }

    public Iterable<Teacher> getTeachers() {
        return teacherRepo.findAll();
    }

    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepo.findById(id);
    }

    public void addTeacher(Teacher teacher) {
        teacherRepo.save(teacher);
    }

    public void removeTeacher(Long id) {
        Optional<Teacher> teacher = teacherRepo.findById(id);
        teacherRepo.delete(teacher.get());
    }

    public void updateTeacher(Long id, Teacher teacher) {
        Optional<Teacher> Tea = teacherRepo.findById(id);
        if(Tea.isPresent()) {
            teacher.setId(Tea.get().getId());
            teacherRepo.save(teacher);
        }
    }
    
    public Iterable<Optional<Teacher>> getTeachersByName(String name){
    	return teacherRepo.findByNameLike("%"+name+"%");
    }
    
    public Optional<Teacher> getTeacherByJmbg(String jmbg){
    	return teacherRepo.findByJmbg(jmbg);
    }

}
