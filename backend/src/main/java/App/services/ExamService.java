package App.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import App.dto.ExamDTO;
import App.dto.ExamRegistrationDTO;
import App.dto.StudentExamRegistrationDTO;
import App.models.Exam;
import App.models.ExamRealization;
import App.models.ExamTopic;
import App.models.ExamType;
import App.models.SubjectAttendance;
import App.repositories.ExamRepository;
import App.repositories.ExamTypeRepository;

@Service
public class ExamService {

	@Autowired
	private ExamRepository examRepo;
	@Autowired
	private ExamTypeRepository examTypeRepo;
	@Autowired
	private ExamTopicService examTopicService;
	@Autowired
	private StudentYearService syService;
	@Autowired
	private ExamRealizationService erService;
	@Autowired
	private SubjectAttendanceService saService;

	public ExamService() {
	}

	public Iterable<Exam> getExams() {
		return examRepo.findAll();
	}

	public Optional<Exam> getExamById(Long id) {
		return examRepo.findById(id);
	}

	@Transactional
	public void addExam(Exam exam) {
		Exam e = new Exam(exam.getStartTime(), exam.getEndTime(), exam.getPoints(), exam.getDurationInMinutes(),
				exam.getSubjectRealization(), exam.getExamType(), exam.getExamRealizations(), exam.getSyllabus());
		e.setSyllabus(null);
		e.setId(examRepo.save(e).getId());
		for (ExamTopic examTopic : exam.getSyllabus()) {
			examTopic.setExam(e);
			examTopicService.addExamTopic(examTopic);
		}
	}

	public void removeExam(Long id) {
		Optional<Exam> exam = examRepo.findById(id);
		examRepo.delete(exam.get());
	}

	public void updateExam(Long id, Exam exam) {
		Optional<Exam> e = examRepo.findById(id);
		if (e.isPresent()) {
			exam.setId(e.get().getId());
			examRepo.save(exam);
		}
	}

	public Iterable<ExamType> getExamTypes() {
		return examTypeRepo.findAll();
	}

	public ArrayList<ExamDTO> getExamsByStudent(Long studentId) {
	
		ArrayList<Object[]> fetched = examRepo.getExamsByStudent(studentId);
		ArrayList<ExamDTO> results = new ArrayList<ExamDTO>();
		try {
			if (fetched != null && fetched.size() > 0) {
				for (int i = 0; i < fetched.size(); i++) {
					results.add(new ExamDTO( (int) fetched.get(i)[0], (String) fetched.get(i)[1], (int) fetched.get(i)[2],
							(String) fetched.get(i)[3], (int) fetched.get(i)[4], (Date) fetched.get(i)[5],
							(int) fetched.get(i)[6]) );
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		

		return results;
	}

	public ArrayList<ExamRegistrationDTO> getAvailableExamsForRegistration(String username) {
		Calendar plusThree = Calendar.getInstance();
		plusThree.setTime(new Date());
		plusThree.add(Calendar.DATE, 3);
		Calendar plusFourteen = Calendar.getInstance();
		plusFourteen.setTime(new Date());
		plusFourteen.add(Calendar.DATE, 14);
		ArrayList<Object[]> available = examRepo.getAvailableExamsForRegistration(username, plusThree.getTime(),
				plusFourteen.getTime());
		ArrayList<ExamRegistrationDTO> exams = new ArrayList<ExamRegistrationDTO>(available.size());
		if (available.size() > 0) {
			for (int i = 0; i < available.size(); i++) {
				exams.add(new ExamRegistrationDTO((Long) available.get(i)[0], (Long) available.get(i)[1],
						(String) available.get(i)[2], (Date) available.get(i)[3], (int) available.get(i)[4],
						(String) available.get(i)[5], (int) available.get(i)[6], (String) available.get(i)[7]));
			}
		}
		return exams;

	}

	@Transactional
	public boolean addGrades(Long subjectId, String teacherUsername,
			ArrayList<StudentExamRegistrationDTO> studentGrades) {

		try {
			Exam test1 = examRepo.getTest1BySubject(subjectId, teacherUsername);
			Exam test2 = examRepo.getTest2BySubject(subjectId, teacherUsername);
			for (StudentExamRegistrationDTO studentReg : studentGrades) {

				// create results of test1
				ExamRealization er = new ExamRealization();
				er.setExam(test1);
				er.setPoints(studentReg.getTest1());
				er.setStudentYear(syService.getStudentYearById(studentReg.getStudentYearId()).get());
				erService.add(er);

				// create results of test2
				ExamRealization er2 = new ExamRealization();
				er2.setExam(test2);
				er2.setPoints(studentReg.getTest2());
				er2.setStudentYear(syService.getStudentYearById(studentReg.getStudentYearId()).get());
				erService.add(er2);

				// update exam registration by adding points to exam realization
				ExamRealization finalE = erService.findById(studentReg.getExamRealizationId()).get();
				finalE.setPoints(studentReg.getFinalExam());
				erService.updateById(studentReg.getExamRealizationId(), finalE);

				// update subject attendance by adding grade
				SubjectAttendance subjAtt = saService.getSubjectAttendanceById(studentReg.getSubjectAttendanceId())
						.get();
				subjAtt.setFinalGrade(studentReg.getGrade());
				saService.updateSubjectAttendance(studentReg.getSubjectAttendanceId(), subjAtt);
			}
			return true;

		} catch (Exception e) {
			return false;
		}

	}

}
