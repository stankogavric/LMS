package App.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.dto.StudentExamRegistrationDTO;
import App.models.Exam;
import App.models.ExamRealization;
import App.repositories.ExamRealizationRepository;
import App.repositories.ExamRepository;
import App.repositories.StudentYearRepository;

@Service
public class ExamRealizationService {

	@Autowired
	private ExamRealizationRepository examRealRepo;

	@Autowired
	private ExamRepository examRepo;

	@Autowired
	private StudentYearRepository syRepo;

	public ArrayList<ExamRealization> getRegisteredExamsByStudent(Long studentId) {
		return examRealRepo.findRegisteredExamsByStudentId(studentId);
	}

	public boolean registerExam(Long examId, Long subjRealId, String studentUsername) {
		try {
			Long studentYearId = syRepo.getStudentYearBySubjectRealizationAndStudent(subjRealId, studentUsername);
			Exam exam = examRepo.findById(examId).get();
			if (studentYearId != null && exam != null) {
				ExamRealization er = new ExamRealization();
				er.setExam(exam);
				er.setNote("");
				er.setPoints(null);
				er.setStudentYear(syRepo.findById(studentYearId).get());
				examRealRepo.saveAndFlush(er);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<StudentExamRegistrationDTO> getRegisteredExamsBySubject(Long subjectId, String teacherUsername) {
		Calendar today = Calendar.getInstance();
		today.setTime(new Date());
		Calendar beforeFifteen = Calendar.getInstance();
		beforeFifteen.setTime(new Date());
		beforeFifteen.add(Calendar.DATE, -15);
		ArrayList<Object[]> fetched = examRealRepo.findRegisteredExamsBySubject(subjectId, teacherUsername,
				beforeFifteen.getTime(), today.getTime());
		ArrayList<StudentExamRegistrationDTO> registrations = new ArrayList<StudentExamRegistrationDTO>(fetched.size());
		if (fetched.size() > 0) {
			try {
				for (int i = 0; i < fetched.size(); i++) {
					registrations.add(new StudentExamRegistrationDTO((String) fetched.get(i)[0],
							(String) fetched.get(i)[1], (String) fetched.get(i)[2], (Long) fetched.get(i)[3],
							(Long) fetched.get(i)[4], (Long) fetched.get(i)[5], (Long) fetched.get(i)[6], (Long) fetched.get(i)[7]));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return registrations;
	}
	
	public void add(ExamRealization er) {
		examRealRepo.save(er);
	}
	
	public Optional<ExamRealization> findById(Long id) {
		return examRealRepo.findById(id);
	}

	public void updateById(Long id, ExamRealization newER) {
		Optional<ExamRealization> er = examRealRepo.findById(id);
		if(er.isPresent()) {
			newER.setId(er.get().getId());
			examRealRepo.save(newER);		
		}
	}

}
