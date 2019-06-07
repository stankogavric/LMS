package App.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import App.models.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	@Query("SELECT t FROM Teacher t WHERE t.personalData.firstName LIKE ?1")
	Iterable<Optional<Teacher>> findTeachersByFirstName(String firstName);
	@Query("SELECT t FROM Teacher t WHERE t.personalData.personalNumber = ?1")
	Optional<Teacher> findTeacherByJmbg(String jmbg);
	@Query("SELECT t FROM Teacher t WHERE t.accountData.username = ?1")
	Optional<Teacher> getByUsername(String username);
	@Query("SELECT DISTINCT tr.teacher FROM TeacherRealization tr WHERE tr.subjectRealization.yearOfStudy.studyProgram.faculty.id = ?1")
	Iterable<Optional<Teacher>> getAllByFaculty(Long facultyId);
}