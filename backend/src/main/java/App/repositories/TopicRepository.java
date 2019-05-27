package App.repositories;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
	ArrayList<Topic> findBySubjectIdEquals(Long subjectId);
	Iterable<Optional<Topic>> findBySubjectId(Long id);
}