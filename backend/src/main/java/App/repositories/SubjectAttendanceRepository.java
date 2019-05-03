package App.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import App.models.Student;
import App.models.Subject;
import App.models.SubjectAttendance;

@Repository
public interface SubjectAttendanceRepository extends JpaRepository<SubjectAttendance, Long> {
	@Query("SELECT AVG(sa.finalGrade) FROM SubjectAttendance sa WHERE sa.student.id = ?1")
	Double findAverageMark(Long studentId);
	
	@Query("SELECT sa.student FROM SubjectAttendance sa WHERE sa.finalGrade < 6 AND sa.subjectRealization.subject.id = ?1")
	Iterable<Student> findStudentsWhoDidntPassExam(Long subjectId);
	
	@Query("SELECT DISTINCT sa.subjectRealization.subject FROM SubjectAttendance sa WHERE sa.student.id = ?1 AND sa.finalGrade = NULL")
	ArrayList<Subject> findCurrentSubjects(Long studentId);
	
	@Query("SELECT sa.subjectRealization.subject, sa.finalGrade FROM SubjectAttendance sa WHERE sa.student.id = ?1")
	ArrayList<Object> findPastSubjects(Long studentId);
}