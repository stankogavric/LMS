package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.ForumUserForumRole;

@Repository
public interface ForumUserForumRoleRepository extends JpaRepository<ForumUserForumRole, Long> {

}