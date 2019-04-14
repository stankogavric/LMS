package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.ScientificField;
import App.repositories.ScientificFieldRepository;

@Service
public class ScientificFieldService {

    @Autowired
    private ScientificFieldRepository scientificFieldRepo;

    public ScientificFieldService() {
    }

    public Iterable<ScientificField> getScientificFields() {
        return scientificFieldRepo.findAll();
    }

    public Optional<ScientificField> getScientificFieldById(Long id) {
        return scientificFieldRepo.findById(id);
    }

    public void addScientificField(ScientificField scientificField) {
        scientificFieldRepo.save(scientificField);
    }

    public void removeScientificField(Long id) {
        Optional<ScientificField> scientificField = scientificFieldRepo.findById(id);
        scientificFieldRepo.delete(scientificField.get());
    }

    public void updateScientificField(Long id, ScientificField scientificField) {
        Optional<ScientificField> Sci = scientificFieldRepo.findById(id);
        if(Sci.isPresent()) {
            scientificField.setId(Sci.get().getId());
            scientificFieldRepo.save(scientificField);
        }
    }

}
