package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.Topic;
import App.repositories.TopicRepository;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepo;

    public TopicService() {
    }

    public Iterable<Topic> getTopics() {
        return topicRepo.findAll();
    }

    public Optional<Topic> getTopicById(Long id) {
        return topicRepo.findById(id);
    }

    public void addTopic(Topic topic) {
        topicRepo.save(topic);
    }

    public void removeTopic(Long id) {
        Optional<Topic> topic = topicRepo.findById(id);
        topicRepo.delete(topic.get());
    }

    public void updateTopic(Long id, Topic topic) {
        Optional<Topic> Top = topicRepo.findById(id);
        if(Top.isPresent()) {
            topic.setId(Top.get().getId());
            topicRepo.save(topic);
        }
    }

}
