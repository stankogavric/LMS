package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.StudentYear;

@Repository
public interface StudentYearRepository extends JpaRepository<StudentYear, Long> {

}