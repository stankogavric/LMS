package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.ExamTopic;

@Repository
public interface ExamTopicRepository extends JpaRepository<ExamTopic, Long> {

}
