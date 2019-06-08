package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.ForumRole;
import App.repositories.ForumRoleRepository;

@Service
public class ForumRoleService {

    @Autowired
    private ForumRoleRepository forumRoleRepo;

    public ForumRoleService() {
    }

    public Iterable<ForumRole> getForumRoles() {
        return forumRoleRepo.findAll();
    }

    public Optional<ForumRole> getForumRoleById(Long id) {
        return forumRoleRepo.findById(id);
    }

    public void addForumRole(ForumRole forumRole) {
        forumRoleRepo.save(forumRole);
    }

    public void removeForumRole(Long id) {
        Optional<ForumRole> forumRole = forumRoleRepo.findById(id);
        forumRoleRepo.delete(forumRole.get());
    }

    public void updateForumRole(Long id, ForumRole forumRole) {
        Optional<ForumRole> For = forumRoleRepo.findById(id);
        if(For.isPresent()) {
            forumRole.setId(For.get().getId());
            forumRoleRepo.save(forumRole);
        }
    }

}
