package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.Forum;
import App.repositories.ForumRepository;

@Service
public class ForumService {

    @Autowired
    private ForumRepository forumRepo;

    public ForumService() {
    }

    public Iterable<Forum> getForums() {
        return forumRepo.findAll();
    }

    public Optional<Forum> getForumById(Long id) {
        return forumRepo.findById(id);
    }

    public void addForum(Forum forum) {
        forumRepo.save(forum);
    }

    public void removeForum(Long id) {
        Optional<Forum> forum = forumRepo.findById(id);
        forumRepo.delete(forum.get());
    }

    public void updateForum(Long id, Forum forum) {
        Optional<Forum> For = forumRepo.findById(id);
        if(For.isPresent()) {
            forum.setId(For.get().getId());
            forumRepo.save(forum);
        }
    }

}
