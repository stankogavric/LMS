package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.SubjectAttendance;

@Repository
public interface SubjectAttendanceRepository extends JpaRepository<SubjectAttendance, Long> {

}