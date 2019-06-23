package App.repositories;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import App.models.ExamRealization;

@Repository
public interface ExamRealizationRepository extends JpaRepository<ExamRealization, Long> {

	@Query("SELECT er.id, er.exam.examType.name, er.exam.startTime, er.exam.endTime, er.exam.subjectRealization.subject.name "
			+ "FROM ExamRealization er " 
			+ "WHERE er.points IS NULL AND er.studentYear.student.id = ?1 AND er.exam.examType.name = 'FINAL' ")
	ArrayList<ExamRealization> findRegisteredExamsByStudentId(Long studentId);

	@Query("SELECT er.studentYear.student.personalData.firstName, er.studentYear.student.personalData.lastName, "
			+ "er.studentYear.numIndex, er.studentYear.student.id,  sr.id, er.id, sa.id, er.studentYear.id "
			+ "FROM ExamRealization er, SubjectRealization sr, TeacherRealization tr, SubjectAttendance sa "
			+ "WHERE er.points = NULL AND er.exam.examType.name = 'FINAL' AND (er.exam.endTime BETWEEN ?3 AND ?4) "
			+ "AND er.exam MEMBER OF sr.exams AND sr.subject.id = ?1 "
			+ "AND tr.teacher.accountData.username = ?2 "
			+ "AND tr.subjectRealization = sr AND sa.subjectRealization = sr "
			+ "AND sa.student = er.studentYear.student")
	ArrayList<Object[]> findRegisteredExamsBySubject(Long subjectId, String teacherUsername, Date beforeFifteen,
			Date today);

}
