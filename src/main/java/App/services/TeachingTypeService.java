package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.TeachingType;
import App.repositories.TeachingTypeRepository;

@Service
public class TeachingTypeService {

    @Autowired
    private TeachingTypeRepository teachingTypeRepo;

    public TeachingTypeService() {
    }

    public Iterable<TeachingType> getTeachingTypes() {
        return teachingTypeRepo.findAll();
    }

    public Optional<TeachingType> getTeachingTypeById(Long id) {
        return teachingTypeRepo.findById(id);
    }

    public void addTeachingType(TeachingType teachingType) {
        teachingTypeRepo.save(teachingType);
    }

    public void removeTeachingType(Long id) {
        Optional<TeachingType> teachingType = teachingTypeRepo.findById(id);
        teachingTypeRepo.delete(teachingType.get());
    }

    public void updateTeachingType(Long id, TeachingType teachingType) {
        Optional<TeachingType> Tea = teachingTypeRepo.findById(id);
        if(Tea.isPresent()) {
            teachingType.setId(Tea.get().getId());
            teachingTypeRepo.save(teachingType);
        }
    }

}
