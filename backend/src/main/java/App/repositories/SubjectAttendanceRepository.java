package App.repositories;

import java.util.ArrayList;
import java.util.Date;

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
	
	@Query("SELECT DISTINCT sa.subjectRealization.subject FROM SubjectAttendance sa WHERE sa.student.accountData.username = ?1 AND sa.finalGrade IS NULL")
	ArrayList<Subject> findCurrentSubjects(String username);
	
	@Query("SELECT sa.finalGrade, sa.subjectRealization.subject.name, sa.subjectRealization.yearOfStudy.year, "
			+ "sa.subjectRealization.yearOfStudy.studyProgram.name, er.points, er.exam.startTime, sa.subjectRealization.subject.ects \n" + 
			"FROM ExamRealization er, SubjectAttendance sa \n" + 
			"WHERE sa.student.accountData.username = ?1 \n" + 
			"AND sa.subjectRealization.id = er.exam.subjectRealization.id "
			+ "AND sa.finalGrade IS NOT NULL")
	ArrayList<Object> findPastSubjects(String username);
	
	@Query("SELECT sa.student FROM SubjectAttendance sa, TeacherRealization tr "
			+ "WHERE sa.subjectRealization.subject.id = ?1 "
			+ "AND sa.subjectRealization.yearOfStudy.startDate <= ?2 "
			+ "AND sa.subjectRealization.yearOfStudy.endDate >= ?2 "
			+ "AND sa.subjectRealization.subject = tr.subjectRealization.subject "
			+ "AND tr.teacher.accountData.username = ?3")
	ArrayList<Student> findStudentsBySubject(Long subjectId, Date today, String teacherUsername);
	
}