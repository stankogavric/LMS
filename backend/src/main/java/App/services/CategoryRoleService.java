package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.CategoryRole;
import App.repositories.CategoryRoleRepository;

@Service
public class CategoryRoleService {

    @Autowired
    private CategoryRoleRepository categoryRoleRepo;

    public CategoryRoleService() {
    }

    public Iterable<CategoryRole> getCategoryRoles() {
        return categoryRoleRepo.findAll();
    }

    public Optional<CategoryRole> getCategoryRoleById(Long id) {
        return categoryRoleRepo.findById(id);
    }

    public void addCategoryRole(CategoryRole categoryRole) {
        categoryRoleRepo.save(categoryRole);
    }

    public void removeCategoryRole(Long id) {
        Optional<CategoryRole> categoryRole = categoryRoleRepo.findById(id);
        categoryRoleRepo.delete(categoryRole.get());
    }

    public void updateCategoryRole(Long id, CategoryRole categoryRole) {
        Optional<CategoryRole> Cat = categoryRoleRepo.findById(id);
        if(Cat.isPresent()) {
            categoryRole.setId(Cat.get().getId());
            categoryRoleRepo.save(categoryRole);
        }
    }

}
