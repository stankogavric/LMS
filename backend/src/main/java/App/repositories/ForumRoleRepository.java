package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.ForumRole;

@Repository
public interface ForumRoleRepository extends JpaRepository<ForumRole, Long> {

}