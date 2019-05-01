package App.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import App.models.StudyProgram;
import App.models.Subject;

@Repository
public interface StudyProgramRepository extends JpaRepository<StudyProgram, Long> {
	@Query("SELECT sp FROM StudyProgram sp, SubjectAttendance sa WHERE AVG(sa.finalGrade) <= 7")
	Iterable<Subject> findSubjectsCandidatedForRemoval(Long studyProgramId);
	ArrayList<StudyProgram> findByFacultyIdEquals(Long id);
}