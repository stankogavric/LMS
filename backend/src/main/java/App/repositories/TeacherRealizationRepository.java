package App.repositories;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import App.models.Subject;
import App.models.Teacher;
import App.models.TeacherRealization;

@Repository
public interface TeacherRealizationRepository extends JpaRepository<TeacherRealization, Long> {
	@Query("SELECT tr.teacher FROM TeacherRealization tr WHERE tr.subjectRealization.subject.id = ?1")
	Iterable<Teacher> findTeachersWhoTeachSubject(Long subjectId);
	@Query("SELECT tr FROM TeacherRealization tr WHERE tr.subjectRealization.yearOfStudy.id = ?1")
	Iterable<Optional<TeacherRealization>> getByYearOfStudy(Long yearOfStudyId);
	
	@Query(
	"SELECT tr.subjectRealization.subject FROM TeacherRealization tr \n"
			+ "WHERE tr.teacher.accountData.username = :username \n"
			+ "AND tr.subjectRealization.yearOfStudy.startDate <= :today \n"
			+ " AND tr.subjectRealization.yearOfStudy.endDate >= :today")
	Iterable<Subject> getTeacherSubjects(@Param("username") String username, @Param("today") Date today);
}