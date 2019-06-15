package App.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import App.dto.ExamRegistrationDTO;
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
    
    public ArrayList<ExamRegistrationDTO> getAvailableExamsForRegistration(String username){
    	Calendar plusThree = Calendar.getInstance();
    	plusThree.setTime(new Date());
    	plusThree.add(Calendar.DATE, 3);
    	Calendar plusFourteen = Calendar.getInstance();
    	plusFourteen.setTime(new Date());
    	plusFourteen.add(Calendar.DATE, 14); 
    	ArrayList<Object[]> available = examRepo.getAvailableExamsForRegistration(username, plusThree.getTime(), plusFourteen.getTime());
    	ArrayList<ExamRegistrationDTO> exams = new ArrayList<ExamRegistrationDTO>(available.size());
    	if(available.size() > 0) {
    		for (int i = 0; i < available.size(); i++) {
        		exams.add(new ExamRegistrationDTO((Long) available.get(i)[0], (Long) available.get(i)[1], (String) available.get(i)[2],
        				(Date) available.get(i)[3], (int) available.get(i)[4], (String) available.get(i)[5], (int) available.get(i)[6], (String) available.get(i)[7]));   			
    		}
    	}
    	return exams;
    	
    }

	
}
