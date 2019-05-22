package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.ExamTopic;
import App.repositories.ExamTopicRepository;

@Service
public class ExamTopicService {
	@Autowired
    private ExamTopicRepository examTopicRepo;

	public ExamTopicService() {
	}
	
	public Iterable<ExamTopic> getExamTopics() {
        return examTopicRepo.findAll();
    }

    public Optional<ExamTopic> getExamTopicById(Long id) {
        return examTopicRepo.findById(id);
    }

    public void addExamTopic(ExamTopic examTopic) {
    	examTopicRepo.save(examTopic);
    }

    public void removeExamTopic(Long id) {
        Optional<ExamTopic> examTopic = examTopicRepo.findById(id);
        examTopicRepo.delete(examTopic.get());
    }

    public void updateExamTopic(Long id, ExamTopic examTopic) {
        Optional<ExamTopic> e = examTopicRepo.findById(id);
        if(e.isPresent()) {
        	examTopic.setId(e.get().getId());
        	examTopicRepo.save(examTopic);
        }
    }
    
}
