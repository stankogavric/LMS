package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.PersonalData;
import App.repositories.PersonalDataRepository;

@Service
public class PersonalDataService {

    @Autowired
    private PersonalDataRepository personalDataRepo;

    public PersonalDataService() {
    }

    public Iterable<PersonalData> getPersonalData() {
        return personalDataRepo.findAll();
    }

    public Optional<PersonalData> getPersonalDataById(Long id) {
        return personalDataRepo.findById(id);
    }
    
    public Optional<PersonalData> getPersonalDataByUsername(String username) {
        return personalDataRepo.getByUsername("%/"+username+".%");
    }

    public void addPersonalData(PersonalData personalData) {
        personalDataRepo.save(personalData);
    }

    public void removePersonalData(Long id) {
        Optional<PersonalData> personalData = personalDataRepo.findById(id);
        personalDataRepo.delete(personalData.get());
    }

    public void updatePersonalData(Long id, PersonalData personalData) {
        Optional<PersonalData> Per = personalDataRepo.findById(id);
        if(Per.isPresent()) {
            personalData.setId(Per.get().getId());
            personalDataRepo.save(personalData);
        }
    }

}
