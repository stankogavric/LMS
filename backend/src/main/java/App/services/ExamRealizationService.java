package App.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
