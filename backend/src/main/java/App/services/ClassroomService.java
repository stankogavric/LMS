package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.Classroom;
import App.repositories.ClassroomRepository;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepo;

    public ClassroomService() {
    }

    public Iterable<Classroom> getClassrooms() {
        return classroomRepo.findAll();
    }

    public Optional<Classroom> getClassroomById(Long id) {
        return classroomRepo.findById(id);
    }

    public void addClassroom(Classroom classroom) {
        classroomRepo.save(classroom);
    }

    public void removeClassroom(Long id) {
        Optional<Classroom> classroom = classroomRepo.findById(id);
        classroomRepo.delete(classroom.get());
    }

    public void updateClassroom(Long id, Classroom classroom) {
        Optional<Classroom> Cla = classroomRepo.findById(id);
        if(Cla.isPresent()) {
            classroom.setId(Cla.get().getId());
            classroomRepo.save(classroom);
        }
    }

}
