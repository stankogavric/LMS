package App.repositories;

import java.util.ArrayList;
import java.util.Date;

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
	
	@Query("SELECT e.id, e.subjectRealization.id, e.subjectRealization.subject.name, e.startTime, e.durationInMinutes, e.examType.name, "
			+ "e.subjectRealization.yearOfStudy.year, e.subjectRealization.yearOfStudy.studyProgram.name "
			+ "FROM Exam e, SubjectAttendance sa "
			+ "WHERE e.subjectRealization = sa.subjectRealization "
			+ "AND sa.finalGrade IS NULL "
			+ "AND sa.student.accountData.username = ?1 "
			+ "AND e.startTime BETWEEN ?2 AND ?3 \n"
			+ "AND e NOT IN (SELECT er.exam FROM ExamRealization er WHERE er.studentYear.student.accountData.username = ?1)")
	ArrayList<Exam> getAvailableExamsForRegistration(String studentUsername, Date plusThree, Date plusFourteen);

}
