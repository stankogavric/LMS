package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.Category;
import App.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    public CategoryService() {
    }

    public Iterable<Category> getCategories() {
        return categoryRepo.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepo.findById(id);
    }

    public void addCategory(Category category) {
        categoryRepo.save(category);
    }

    public void removeCategory(Long id) {
        Optional<Category> category = categoryRepo.findById(id);
        categoryRepo.delete(category.get());
    }

    public void updateCategory(Long id, Category category) {
        Optional<Category> Cat = categoryRepo.findById(id);
        if(Cat.isPresent()) {
            category.setId(Cat.get().getId());
            categoryRepo.save(category);
        }
    }

}
