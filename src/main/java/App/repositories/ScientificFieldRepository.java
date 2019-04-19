package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.ScientificField;

@Repository
public interface ScientificFieldRepository extends JpaRepository<ScientificField, Long> {

}