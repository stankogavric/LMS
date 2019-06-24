package App.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import App.models.AdministrativeStaff;
import App.models.ElectiveSubjectAttendance;
import App.models.Student;
import App.models.StudentYear;
import App.models.Subject;
import App.models.SubjectAttendance;
import App.models.SubjectRealization;
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
    
    @Autowired
	private PasswordEncoder passwordEncoder;
    
    @Autowired
    private SubjectService subjectService;
    
    @Autowired
    private SubjectRealizationService subjectRealizationService;
    
    @Autowired
    private SubjectAttendanceService subjectAttendanceService;
    
    public AdministrativeStaffService() {
    }

    public Iterable<AdministrativeStaff> getAdministrativeStaff() {
        return administrativeStaffRepo.findAll();
    }

    public Optional<AdministrativeStaff> getAdministrativeStaffById(Long id) {
        return administrativeStaffRepo.findById(id);
    }
    
    public Optional<AdministrativeStaff> getAdministrativeStaffByUsername(String username) {
        return administrativeStaffRepo.getByUsername(username);
    }

    public void addAdministrativeStaff(AdministrativeStaff administrativeStaff) {
    	loginServ.addPermsion(administrativeStaff.getAccountData(), "ROLE_ADMINISTRATIVE_STAFF");
    	administrativeStaff.getAccountData().setPassword(passwordEncoder.encode(administrativeStaff.getAccountData().getPassword()));
        administrativeStaffRepo.save(administrativeStaff);
    }
    
    public void removeAdministrativeStaff(Long id) {
        Optional<AdministrativeStaff> administrativeStaff = administrativeStaffRepo.findById(id);
        AdministrativeStaff a = administrativeStaff.get();
        a.setDeleted(true);
        administrativeStaffRepo.save(a);
    }

    public void updateAdministrativeStaff(String username, AdministrativeStaff administrativeStaff) {
        Optional<AdministrativeStaff> Adm = administrativeStaffRepo.getByUsername(username);
        if(Adm.isPresent()) {
            administrativeStaff.setId(Adm.get().getId());
            administrativeStaff.getAccountData().setPassword(passwordEncoder.encode(administrativeStaff.getAccountData().getPassword()));
            accountServ.updateAccountData(administrativeStaff.getAccountData().getId(), administrativeStaff.getAccountData());
            addressServ.updateAddress(administrativeStaff.getAddress().getId(), administrativeStaff.getAddress());
            personalServ.updatePersonalData(administrativeStaff.getPersonalData().getId(), administrativeStaff.getPersonalData());
        }
    }
    
    public Iterable<Student> getStudentsForEnrollmentToTheNextYear(Long yearOfStudyId) {
    	ArrayList<Student> studentsForEnrollmentToTheNextYear = new ArrayList<Student>();
        Optional<YearOfStudy> nextYearOfStudy = yearOfStudyService.getNextYearOfStudyByStudyProgram(yearOfStudyId);
        if(!nextYearOfStudy.isPresent()) { // if there is a study program then there are also all study years
        	return studentsForEnrollmentToTheNextYear;
        }
        ArrayList<Subject> prerequisites = (ArrayList<Subject>) subjectService.getPrerequisitesForMandatorySubjectsByYearOfStudy(nextYearOfStudy.get().getId());
        ArrayList<Student> tempStudents = (ArrayList<Student>) administrativeStaffRepo.findStudentsForEnrollmentToTheNextYear(yearOfStudyId);
    	
    	int prerequisitesSize = prerequisites.size();
    	if(prerequisitesSize == 0) {
    		studentsForEnrollmentToTheNextYear = (ArrayList<Student>) tempStudents;
    		return studentsForEnrollmentToTheNextYear;
    	}
    	for(int i = 0; i < tempStudents.size(); i++) {
//		for(Student s: tempStudents) {
			int passedPrerequisites = 0;
			for(SubjectAttendance sa: tempStudents.get(i).getSubjectAttendances()) {
				for(Subject prerequisite: prerequisites) {
    				if(sa.getFinalGrade() != null && prerequisite.getId() == sa.getSubjectRealization().getSubject().getId()) {
    					passedPrerequisites = passedPrerequisites + 1;
    				}
				}
			}
			if(passedPrerequisites == prerequisitesSize) {
				studentsForEnrollmentToTheNextYear.add(tempStudents.get(i));
			}	
			
		}
    	
    	return studentsForEnrollmentToTheNextYear;
    }
    
//    public Iterable<Student> getStudentsForEnrollmentToTheNextYear(String studyProgram, int yearOfStudy) {
//        return administrativeStaffRepo.findStudentsForEnrollmentToTheNextYear(studyProgram, yearOfStudy);
//    }
    
    public Boolean enrollmentStudentToTheNextYear(Long studentId, YearOfStudy yearOfStudy) { 
    	Optional<Student> student = studentService.getStudentById(studentId);
        if(student.isPresent()) {
        	Optional<YearOfStudy> oldYearOfStudy = yearOfStudyService.getYearOfStudyById(yearOfStudy.getId());
        	Optional<YearOfStudy> newYearOfStudy = yearOfStudyService.getNextYearOfStudyByStudyProgram(yearOfStudy.getId());
        	Optional<StudentYear> studentYear = studentYearService.getStudentYearByYearOfStudyIdAndStudentId(oldYearOfStudy.get().getId(), student.get().getId());
        	studentYear.get().setYearOfStudy(newYearOfStudy.get());
        	studentYearService.updateStudentYear(studentYear.get().getId(), studentYear.get());
        	
        	Iterable<SubjectRealization> subjectRealizations = subjectRealizationService.getSubjectRealizationByYearOfStudyId(newYearOfStudy.get().getId());
        	for(SubjectRealization sr: subjectRealizations) {
        		if(sr.getSubject().getMandatory()) {
        			subjectAttendanceService.addSubjectAttendance(new SubjectAttendance(null, student.get(), sr));
        		}
        		else if(!sr.getSubject().getMandatory()) {
        			for(ElectiveSubjectAttendance esa: student.get().getElectiveSubjectAttendances()) {
        				if(esa.getSubjectRealization().getId() == sr.getId()) {
        					subjectAttendanceService.addSubjectAttendance(new SubjectAttendance(null, student.get(), sr));
        				}
        			}
        		}
        	}
        	return true;
        }
        return false;
    }
    
//    public Boolean enrollmentStudentToTheNextYear(ArrayList<String> ids) { 
//    	Optional<Student> student = studentService.getStudentById(Long.valueOf(ids.get(0)));
//    	if(student.isPresent()) {
//    		Student s = student.get();
//    		s.setYearOfStudy(s.getYearOfStudy()+1);
//    		Optional<YearOfStudy> oldYos = yearOfStudyService.getYearOfStudyById(Long.valueOf(ids.get(1)));
//    		Optional<YearOfStudy> yos = yearOfStudyService.getNextYearOfStudyByStudyProgram(Long.valueOf(ids.get(1)));
//    		Optional<StudentYear> sy = studentYearService.getStudentYearByYearOfStudyId(oldYos.get().getId());
//    		sy.get().setYearOfStudy(yos.get());
//    		studentYearService.updateStudentYear(sy.get().getId(), sy.get());
//    		return true;
//    	}
//        return false;
//    }

}
