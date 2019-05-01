package App.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.UniversityPhones;

@Repository
public interface UniversityPhonesRepository extends JpaRepository<UniversityPhones, Long> {
	ArrayList<UniversityPhones> findByUniversityIdEquals(Long id);
}
