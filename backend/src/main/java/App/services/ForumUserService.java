package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.ForumUser;
import App.repositories.ForumUserRepository;

@Service
public class ForumUserService {

    @Autowired
    private ForumUserRepository forumUserRepo;

    public ForumUserService() {
    }

    public Iterable<ForumUser> getForumUsers() {
        return forumUserRepo.findAll();
    }

    public Optional<ForumUser> getForumUserById(Long id) {
        return forumUserRepo.findById(id);
    }

    public void addForumUser(ForumUser forumUser) {
        forumUserRepo.save(forumUser);
    }

    public void removeForumUser(Long id) {
        Optional<ForumUser> forumUser = forumUserRepo.findById(id);
        forumUserRepo.delete(forumUser.get());
    }

    public void updateForumUser(Long id, ForumUser forumUser) {
        Optional<ForumUser> For = forumUserRepo.findById(id);
        if(For.isPresent()) {
            forumUser.setId(For.get().getId());
            forumUserRepo.save(forumUser);
        }
    }

}
