package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.Exam;
import App.models.ExamType;
import App.repositories.ExamRepository;
import App.repositories.ExamTypeRepository;

@Service
public class ExamService {

	@Autowired
    private ExamRepository examRepo;
	@Autowired
    private ExamTypeRepository examTypeRepo;

	public ExamService() {
	}
	
	public Iterable<Exam> getExams() {
        return examRepo.findAll();
    }

    public Optional<Exam> getExamById(Long id) {
        return examRepo.findById(id);
    }

    public void addExam(Exam exam) {
    	examRepo.save(exam);
    }

    public void removeExam(Long id) {
        Optional<Exam> exam = examRepo.findById(id);
        examRepo.delete(exam.get());
    }

    public void updateExam(Long id, Exam exam) {
        Optional<Exam> e = examRepo.findById(id);
        if(e.isPresent()) {
        	exam.setId(e.get().getId());
            examRepo.save(exam);
        }
    }
    
    public Iterable<ExamType> getExamTypes() {
    	return examTypeRepo.findAll();
    }
	
}
