package App.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import App.models.Permission;

public interface PermissionRepository extends JpaRepository <Permission, Long> {

	Optional<Permission> getByTitle(String string);
	
}

