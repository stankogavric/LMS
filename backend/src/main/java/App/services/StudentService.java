package App.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.Student;
import App.repositories.StudentCustomRepositoryImpl;
import App.repositories.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepo;
    
    @Autowired
    private StudentCustomRepositoryImpl studentCustomRepo;

    @Autowired
    private LoginService loginServ;
    
    public StudentService() {
    }

    public Iterable<Student> getStudents() {
        return studentRepo.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepo.findById(id);
    }

    public void addStudent(Student student) {
    	loginServ.addPermsion(student.getAccountData(), "ROLE_STUDENT");
        studentRepo.save(student);
    }

    public void removeStudent(Long id) {
        Optional<Student> student = studentRepo.findById(id);
        Student s = student.get();
        s.setDeleted(true);
        studentRepo.save(s);
    }

    public void updateStudent(Long id, Student student) {
        Optional<Student> Stu = studentRepo.findById(id);
        if(Stu.isPresent()) {
            student.setId(Stu.get().getId());
            studentRepo.save(student);
        }
    }
    
    public Iterable<Optional<Student>> getStudentsByFirstName(String firstName){
    	return studentRepo.findStudentsByFirstName("%"+firstName+"%");
    }
    
    public Optional<Student> getStudentByJmbg(String jmbg){
    	return studentRepo.findStudentByJmbg(jmbg);
    }
    
    public Collection<Student> searchStudents(String firstName, String lastName, String indexNum, String enrolment, String avgGrade){    	
    	return studentCustomRepo.searchStudents(firstName, lastName, indexNum, enrolment, avgGrade);
		
    }

}
