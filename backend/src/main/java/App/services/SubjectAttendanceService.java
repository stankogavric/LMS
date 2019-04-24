package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.Student;
import App.models.SubjectAttendance;
import App.repositories.SubjectAttendanceRepository;

@Service
public class SubjectAttendanceService {

    @Autowired
    private SubjectAttendanceRepository subjectAttendanceRepo;

    public SubjectAttendanceService() {
    }

    public Iterable<SubjectAttendance> getSubjectAttendances() {
        return subjectAttendanceRepo.findAll();
    }

    public Optional<SubjectAttendance> getSubjectAttendanceById(Long id) {
        return subjectAttendanceRepo.findById(id);
    }

    public void addSubjectAttendance(SubjectAttendance subjectAttendance) {
        subjectAttendanceRepo.save(subjectAttendance);
    }

    public void removeSubjectAttendance(Long id) {
        Optional<SubjectAttendance> subjectAttendance = subjectAttendanceRepo.findById(id);
        subjectAttendanceRepo.delete(subjectAttendance.get());
    }

    public void updateSubjectAttendance(Long id, SubjectAttendance subjectAttendance) {
        Optional<SubjectAttendance> Sub = subjectAttendanceRepo.findById(id);
        if(Sub.isPresent()) {
            subjectAttendance.setId(Sub.get().getId());
            subjectAttendanceRepo.save(subjectAttendance);
        }
    }
    
    public Double getAverageMark(Long studentId) {
        return subjectAttendanceRepo.findAverageMark(studentId);
    }
    
    public Iterable<Student> getStudentsWhoDidntPassExam(Long subjectId) {
        return subjectAttendanceRepo.findStudentsWhoDidntPassExam(subjectId);
    }

}
