package App.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.YearOfStudy;

@Repository
public interface YearOfStudyRepository extends JpaRepository<YearOfStudy, Long> {
	ArrayList<YearOfStudy> findByStudyProgramIdEquals(Long studyProgramId);
}