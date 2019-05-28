package App.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.AdministrativeStaff;
import App.models.Student;
import App.models.StudentYear;
import App.models.YearOfStudy;
import App.repositories.AdministrativeStaffRepository;

@Service
public class AdministrativeStaffService {

    @Autowired
    private AdministrativeStaffRepository administrativeStaffRepo;

    @Autowired
    private LoginService loginServ;
    
    @Autowired
    private AccountDataService accountServ;
    
    @Autowired
    private AddressService addressServ;
    
    @Autowired
    private PersonalDataService personalServ;
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private YearOfStudyService yearOfStudyService;
    
    @Autowired
    private StudentYearService studentYearService;
    
    public AdministrativeStaffService() {
    }

    public Iterable<AdministrativeStaff> getAdministrativeStaff() {
        return administrativeStaffRepo.findAll();
    }

    public Optional<AdministrativeStaff> getAdministrativeStaffById(Long id) {
        return administrativeStaffRepo.findById(id);
    }

    public void addAdministrativeStaff(AdministrativeStaff administrativeStaff) {
    	loginServ.addPermsion(administrativeStaff.getAccountData(), "ROLE_ADMINISTRATIVE_STAFF");
        administrativeStaffRepo.save(administrativeStaff);
    }
    
    public void removeAdministrativeStaff(Long id) {
        Optional<AdministrativeStaff> administrativeStaff = administrativeStaffRepo.findById(id);
        AdministrativeStaff a = administrativeStaff.get();
        a.setDeleted(true);
        administrativeStaffRepo.save(a);
    }

    public void updateAdministrativeStaff(Long id, AdministrativeStaff administrativeStaff) {
        Optional<AdministrativeStaff> Adm = administrativeStaffRepo.findById(id);
        if(Adm.isPresent()) {
            administrativeStaff.setId(Adm.get().getId());
            accountServ.updateAccountData(administrativeStaff.getAccountData().getId(), administrativeStaff.getAccountData());
            addressServ.updateAddress(administrativeStaff.getAddress().getId(), administrativeStaff.getAddress());
            personalServ.updatePersonalData(administrativeStaff.getPersonalData().getId(), administrativeStaff.getPersonalData());
        }
    }
    
    public Iterable<Student> getStudentsForEnrollmentToTheNextYear(String studyProgram, int yearOfStudy) {
        return administrativeStaffRepo.findStudentsForEnrollmentToTheNextYear(studyProgram, yearOfStudy);
    }
    
    public Boolean enrollmentStudentToTheNextYear(ArrayList<String> ids) { 
    	Optional<Student> student = studentService.getStudentById(Long.valueOf(ids.get(0)));
    	if(student.isPresent()) {
    		Student s = student.get();
    		s.setYearOfStudy(s.getYearOfStudy()+1);
    		Optional<YearOfStudy> oldYos = yearOfStudyService.getYearOfStudyById(Long.valueOf(ids.get(1)));
    		Optional<YearOfStudy> yos = yearOfStudyService.getNextYearOfStudyByStudyProgram(Long.valueOf(ids.get(1)));
    		Optional<StudentYear> sy = studentYearService.getStudentYearByYearOfStudyId(oldYos.get().getId());
    		sy.get().setYearOfStudy(yos.get());
    		studentYearService.updateStudentYear(sy.get().getId(), sy.get());
    		return true;
    	}
        return false;
    }

}
