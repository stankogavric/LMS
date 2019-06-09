package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.ForumTopic;
import App.repositories.ForumTopicRepository;

@Service
public class ForumTopicService {

    @Autowired
    private ForumTopicRepository forumTopicRepo;

    public ForumTopicService() {
    }

    public Iterable<ForumTopic> getForumTopics() {
        return forumTopicRepo.findAll();
    }

    public Optional<ForumTopic> getForumTopicById(Long id) {
        return forumTopicRepo.findById(id);
    }

    public void addForumTopic(ForumTopic forumTopic) {
        forumTopicRepo.save(forumTopic);
    }

    public void removeForumTopic(Long id) {
        Optional<ForumTopic> forumTopic = forumTopicRepo.findById(id);
        forumTopicRepo.delete(forumTopic.get());
    }

    public void updateForumTopic(Long id, ForumTopic forumTopic) {
        Optional<ForumTopic> For = forumTopicRepo.findById(id);
        if(For.isPresent()) {
            forumTopic.setId(For.get().getId());
            forumTopicRepo.save(forumTopic);
        }
    }

}
