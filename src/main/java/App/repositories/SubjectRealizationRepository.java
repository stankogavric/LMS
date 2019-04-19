package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.SubjectRealization;

@Repository
public interface SubjectRealizationRepository extends JpaRepository<SubjectRealization, Long> {

}