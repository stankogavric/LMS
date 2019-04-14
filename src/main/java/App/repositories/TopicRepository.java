package App.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import App.models.Topic;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Long> {

}