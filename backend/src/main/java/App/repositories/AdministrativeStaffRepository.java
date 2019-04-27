package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.AdministrativeStaff;

@Repository
public interface AdministrativeStaffRepository extends JpaRepository<AdministrativeStaff, Long> {

}