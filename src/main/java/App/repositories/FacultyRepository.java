package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

}