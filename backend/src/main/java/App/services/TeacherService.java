package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    
    @Autowired
    private AccountDataService accountServ;
    
    @Autowired
    private AddressService addressServ;
    
    @Autowired
    private PersonalDataService personalServ;
    
    @Autowired
	private PasswordEncoder passwordEncoder;

    public TeacherService() {
    }

    public Iterable<Teacher> getTeachers() {
        return teacherRepo.findAll();
    }
    
    public Iterable<Optional<Teacher>> getTeachersByFaculty(Long facultyId) {
        return teacherRepo.getAllByFaculty(facultyId);
    }

    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepo.findById(id);
    }
    
    public Optional<Teacher> getTeacherByUsername(String username) {
        return teacherRepo.getByUsername(username);
    }

    public void addTeacher(Teacher teacher) {
    	loginServ.addPermsion(teacher.getAccountData(), "ROLE_TEACHER");
    	teacher.getAccountData().setPassword(passwordEncoder.encode(teacher.getAccountData().getPassword()));
        teacherRepo.save(teacher);
    }

    public void removeTeacher(Long id) {
        Optional<Teacher> teacher = teacherRepo.findById(id);
        Teacher t = teacher.get();
        t.setDeleted(true);
        teacherRepo.save(t);
    }

    public void updateTeacher(String username, Teacher teacher) {
        Optional<Teacher> Tea = teacherRepo.getByUsername(username);
        if(Tea.isPresent()) {
            teacher.setId(Tea.get().getId());
            teacher.getAccountData().setPassword(passwordEncoder.encode(teacher.getAccountData().getPassword()));
            accountServ.updateAccountData(teacher.getAccountData().getId(), teacher.getAccountData());
            addressServ.updateAddress(teacher.getAddress().getId(), teacher.getAddress());
            personalServ.updatePersonalData(teacher.getPersonalData().getId(), teacher.getPersonalData());
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
