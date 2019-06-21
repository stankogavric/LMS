package App.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import App.models.AdministrativeStaff;
import App.models.Student;

@Repository
public interface AdministrativeStaffRepository extends JpaRepository<AdministrativeStaff, Long> {
//	@Query("SELECT DISTINCT s FROM Student s JOIN s.studentYears sy JOIN s.subjectAttendances sa JOIN sa.subjectRealization sr WHERE s.yearOfStudy=?2 AND sy.yearOfStudy.studyProgram.name=?1 AND sa.finalGrade IS NOT NULL GROUP BY s.id HAVING (SUM(sr.subject.ects))>=(48*(s.yearOfStudy))")
//	Iterable<Student> findStudentsForEnrollmentToTheNextYear(String studyProgram, int yearOfStudy);
	@Query("SELECT DISTINCT s, sy.yearOfStudy.year FROM Student s JOIN s.studentYears sy JOIN s.subjectAttendances sa JOIN sa.subjectRealization sr WHERE sy.yearOfStudy.id=?1 AND sa.finalGrade IS NOT NULL GROUP BY s.id HAVING (SUM(sr.subject.ects))>=(48*(sy.yearOfStudy.year))")
	Iterable<Student> findStudentsForEnrollmentToTheNextYear(Long yearOfStudyId);
	@Query("SELECT a FROM AdministrativeStaff a WHERE a.AccountData.username = ?1")
	Optional<AdministrativeStaff> getByUsername(String username);
}