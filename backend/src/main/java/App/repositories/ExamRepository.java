package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

}
