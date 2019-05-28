package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.Student;
import App.repositories.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepo;
    
    @Autowired
    private AccountDataService accountServ;
    
    @Autowired
    private AddressService addressServ;
    
    @Autowired
    private PersonalDataService personalServ;

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
            accountServ.updateAccountData(student.getAccountData().getId(), student.getAccountData());
            addressServ.updateAddress(student.getAddress().getId(), student.getAddress());
            personalServ.updatePersonalData(student.getPersonalData().getId(), student.getPersonalData());
        }
    }
    
    public Iterable<Optional<Student>> getStudentsByFirstName(String firstName){
    	return studentRepo.findStudentsByFirstName("%"+firstName+"%");
    }
    
    public Optional<Student> getStudentByJmbg(String jmbg){
    	return studentRepo.findStudentByJmbg(jmbg);
    }

}
