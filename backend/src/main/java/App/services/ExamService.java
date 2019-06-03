package App.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import App.models.Exam;
import App.models.ExamTopic;
import App.models.ExamType;
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
    	Exam e = new Exam(exam.getStartTime(), exam.getEndTime(), exam.getPoints(), exam.getDurationInMinutes(), exam.getSubjectRealization(), exam.getExamType(), exam.getExamRealizations(), exam.getSyllabus());
    	e.setSyllabus(null);
    	e.setId(examRepo.save(e).getId());
    	for(ExamTopic examTopic: exam.getSyllabus()) {
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
        if(e.isPresent()) {
        	exam.setId(e.get().getId());
            examRepo.save(exam);
        }
    }
    
    public Iterable<ExamType> getExamTypes() {
    	return examTypeRepo.findAll();
    }
    
    public ArrayList<Object> getExamsByStudent(Long studentId){
    	return examRepo.getExamsByStudent(studentId);
    	
    }

	
}
