package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

}