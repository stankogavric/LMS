package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.ForumUserForumRole;
import App.repositories.ForumUserForumRoleRepository;

@Service
public class ForumUserForumRoleService {

    @Autowired
    private ForumUserForumRoleRepository forumUserForumRoleRepo;

    public ForumUserForumRoleService() {
    }

    public Iterable<ForumUserForumRole> getForumUserForumRoles() {
        return forumUserForumRoleRepo.findAll();
    }

    public Optional<ForumUserForumRole> getForumUserForumRoleById(Long id) {
        return forumUserForumRoleRepo.findById(id);
    }

    public void addForumUserForumRole(ForumUserForumRole forumUserForumRole) {
        forumUserForumRoleRepo.save(forumUserForumRole);
    }

    public void removeForumUserForumRole(Long id) {
        Optional<ForumUserForumRole> forumUserForumRole = forumUserForumRoleRepo.findById(id);
        forumUserForumRoleRepo.delete(forumUserForumRole.get());
    }

    public void updateForumUserForumRole(Long id, ForumUserForumRole forumUserForumRole) {
        Optional<ForumUserForumRole> For = forumUserForumRoleRepo.findById(id);
        if(For.isPresent()) {
            forumUserForumRole.setId(For.get().getId());
            forumUserForumRoleRepo.save(forumUserForumRole);
        }
    }

}
