package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.ForumTopic;

@Repository
public interface ForumTopicRepository extends JpaRepository<ForumTopic, Long> {

}