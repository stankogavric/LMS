package App.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import App.models.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
	@Query("SELECT sa.finalGrade, sa.subjectRealization.subject.name, sa.subjectRealization.yearOfStudy.year, sa.subjectRealization.yearOfStudy.studyProgram.name, er.points, er.exam.startTime, sa.subjectRealization.subject.ects "
			+ "FROM ExamRealization er, SubjectAttendance sa "
			+ "WHERE sa.student.id = ?1 "
			+ "AND sa.subjectRealization.id = er.exam.subjectRealization.id")
	ArrayList<Object> getExamsByStudent(Long studentId);

}
