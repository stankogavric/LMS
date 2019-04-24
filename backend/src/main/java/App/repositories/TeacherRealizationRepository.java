package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import App.models.Teacher;
import App.models.TeacherRealization;

@Repository
public interface TeacherRealizationRepository extends JpaRepository<TeacherRealization, Long> {
	@Query("SELECT tr.teacher FROM TeacherRealization tr WHERE tr.subjectRealization.subject.id = ?1")
	Iterable<Teacher> findTeachersWhoTeachSubject(Long subjectId);
}