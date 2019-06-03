package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.Classroom;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

}
