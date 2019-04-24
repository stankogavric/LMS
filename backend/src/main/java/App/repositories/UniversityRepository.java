package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {

}