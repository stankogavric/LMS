package App.repositories;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import App.models.YearOfStudy;

@Repository
public interface YearOfStudyRepository extends JpaRepository<YearOfStudy, Long> {
	ArrayList<YearOfStudy> findByStudyProgramIdEquals(Long studyProgramId);
	@Query("SELECT y from YearOfStudy y WHERE y.year=?1 AND y.studyProgram.id=?2")
	Optional<YearOfStudy> getNextYearOfStudy(int year, Long id);
}