package App.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import App.models.Dissertation;

@Repository
public interface DissertationRepository extends JpaRepository<Dissertation, Long> {
	
	@Query("SELECT diss.title, diss.applicationDate, diss.defenseDate, diss.file.url, "
			+ "diss.mentor.personalData.firstName, diss.mentor.personalData.lastName, "
			+ "diss.studentYear.yearOfStudy.studyProgram.name "
			+ "FROM Dissertation diss WHERE diss.studentYear.student.id = ?1") 
	ArrayList<Object> findByStudentYearStudentId(Long studentId);
	

}
