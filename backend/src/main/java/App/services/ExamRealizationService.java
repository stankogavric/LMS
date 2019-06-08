package App.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.ExamRealization;
import App.repositories.ExamRealizationRepository;

@Service
public class ExamRealizationService {
	
	@Autowired
	private ExamRealizationRepository examRealRepo;
	
	public ArrayList<ExamRealization> getRegisteredExamsByStudent(Long studentId){
		return examRealRepo.findRegisteredExamsByStudentId(studentId);
	}
	
	

}
