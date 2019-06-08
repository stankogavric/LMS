package App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import App.models.CategoryRole;

@Repository
public interface CategoryRoleRepository extends JpaRepository<CategoryRole, Long> {

}