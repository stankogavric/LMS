package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

}