package App.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.FacultyPhones;

@Repository
public interface FacultyPhonesRepository extends JpaRepository<FacultyPhones, Long> {
	ArrayList<FacultyPhones> findByFacultyIdEquals(Long id);
}