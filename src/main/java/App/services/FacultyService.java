package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.Faculty;
import App.repositories.FacultyRepository;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepo;

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

}
