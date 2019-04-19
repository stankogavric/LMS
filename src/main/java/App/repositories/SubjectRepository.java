package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

}