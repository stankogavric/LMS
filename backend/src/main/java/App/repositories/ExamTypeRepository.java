package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.ExamType;

@Repository
public interface ExamTypeRepository extends JpaRepository<ExamType, Long> {

}
