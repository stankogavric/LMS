package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.TeachingMaterial;
import App.repositories.TeachingMaterialRepository;

@Service
public class TeachingMaterialService {
	
	@Autowired
    private TeachingMaterialRepository teachingMaterialRepo;

    public TeachingMaterialService() {
    }

    public Iterable<TeachingMaterial> getTeachingMaterials() {
        return teachingMaterialRepo.findAll();
    }
    
    public Iterable<Optional<TeachingMaterial>> getTeachingMaterialsBySubject(Long id) {
        return teachingMaterialRepo.getAllBySubject(id);
    }

    public Optional<TeachingMaterial> getTeachingMaterialById(Long id) {
        return teachingMaterialRepo.findById(id);
    }

    public void addTeachingMaterial(TeachingMaterial teachingMaterial) {
        teachingMaterialRepo.save(teachingMaterial);
    }

    public void removeTeachingMaterial(Long id) {
        Optional<TeachingMaterial> teachingMaterial = teachingMaterialRepo.findById(id);
        teachingMaterialRepo.delete(teachingMaterial.get());
    }

    public void updateTeachingMaterial(Long id, TeachingMaterial teachingMaterial) {
        Optional<TeachingMaterial> Tea = teachingMaterialRepo.findById(id);
        if(Tea.isPresent()) {
            teachingMaterial.setId(Tea.get().getId());
            teachingMaterialRepo.save(teachingMaterial);
        }
    }

}

