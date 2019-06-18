package App.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import App.models.StudentYear;

@Repository
public interface StudentYearRepository extends JpaRepository<StudentYear, Long> {
	Optional<StudentYear> findByYearOfStudyIdAndStudentId(Long yearOfStudyId, Long studentId);
	
	@Query("SELECT sy.id FROM StudentYear sy JOIN sy.yearOfStudy.subjectRealizations sr "
			+ "WHERE  sr.id = ?1 "
			+ "AND sy.student.accountData.username = ?2")
	Long getStudentYearBySubjectRealizationAndStudent(Long subjRealId, String studentUsername);
}