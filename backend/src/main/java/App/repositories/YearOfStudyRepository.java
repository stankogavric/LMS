package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.YearOfStudy;

@Repository
public interface YearOfStudyRepository extends JpaRepository<YearOfStudy, Long> {

}