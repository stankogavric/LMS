package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.StudentYear;
import App.repositories.StudentYearRepository;

@Service
public class StudentYearService {

    @Autowired
    private StudentYearRepository studentYearRepo;

    public StudentYearService() {
    }

    public Iterable<StudentYear> getStudentsYear() {
        return studentYearRepo.findAll();
    }

    public Optional<StudentYear> getStudentYearById(Long id) {
        return studentYearRepo.findById(id);
    }

    public void addStudentYear(StudentYear studentYear) {
        studentYearRepo.save(studentYear);
    }

    public void removeStudentYear(Long id) {
        Optional<StudentYear> studentYear = studentYearRepo.findById(id);
        studentYearRepo.delete(studentYear.get());
    }

    public void updateStudentYear(Long id, StudentYear studentYear) {
        Optional<StudentYear> Stu = studentYearRepo.findById(id);
        if(Stu.isPresent()) {
            studentYear.setId(Stu.get().getId());
            studentYearRepo.save(studentYear);
        }
    }
    
    public Optional<StudentYear> getStudentYearByYearOfStudyIdAndStudentId(Long yearOfStudyId, Long studentId) {
        return studentYearRepo.findByYearOfStudyIdAndStudentId(yearOfStudyId, studentId);
    }

}
