package App.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import App.models.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
	@Query("SELECT a FROM Administrator a WHERE a.accountData.username = ?1")
	Optional<Administrator> getByUsername(String username);
}