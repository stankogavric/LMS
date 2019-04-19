package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.TeacherRealization;

@Repository
public interface TeacherRealizationRepository extends JpaRepository<TeacherRealization, Long> {

}