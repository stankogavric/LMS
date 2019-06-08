package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.Forum;

@Repository
public interface ForumRepository extends JpaRepository<Forum, Long> {

}