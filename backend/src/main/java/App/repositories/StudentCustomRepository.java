package App.repositories;

import java.util.Collection;

import App.models.Student;

public interface StudentCustomRepository {
	Collection<Student> searchStudents(String first, String last, String indexNum, String enrolment, String avgGrade);

}
