package App.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import App.models.ExamRealization;

@Repository
public interface ExamRealizationRepository extends JpaRepository<ExamRealization, Long> {
	
	@Query("SELECT er.id, er.exam.examType.name, er.exam.startTime, er.exam.endTime, er.exam.subjectRealization.subject.name "
			+ "FROM ExamRealization er "
			+ "WHERE er.points IS NULL AND  er.studentYear.student.id = ?1")
	ArrayList<ExamRealization> findRegisteredExamsByStudentId(Long studentId);
	
}
