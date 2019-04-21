package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import App.models.StudyProgram;

@Repository
public interface StudyProgramRepository extends JpaRepository<StudyProgram, Long> {
	@Query("SELECT sp FROM StudyProgram sp, SubjectAttendance sa WHERE AVG(sa.finalGrade) <= 7")
	StudyProgram findStudyProgramsCandidatedForRemoval(StudyProgram studyProgram);
}