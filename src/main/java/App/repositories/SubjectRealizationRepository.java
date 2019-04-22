package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import App.models.SubjectRealization;
import App.models.Teacher;

@Repository
public interface SubjectRealizationRepository extends JpaRepository<SubjectRealization, Long> {
	@Query("SELECT tr.teacher FROM TeacherRealization tr WHERE tr.teachingType.name='Exercises' AND tr.subjectRealization.subject.id = ?1")
	Iterable<Teacher> findTeachersWhoTeachExercises(Long subjectId);
}