package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.TeachingType;

@Repository
public interface TeachingTypeRepository extends JpaRepository<TeachingType, Long> {

}