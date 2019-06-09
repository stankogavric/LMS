package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.ForumUser;

@Repository
public interface ForumUserRepository extends JpaRepository<ForumUser, Long> {

}