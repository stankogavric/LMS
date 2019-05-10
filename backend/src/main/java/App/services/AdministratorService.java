package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.Administrator;
import App.repositories.AdministratorRepository;

@Service
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepo;

    @Autowired
    private LoginService loginServ;
    
    public AdministratorService() {
    }

    public Iterable<Administrator> getAdministrators() {
        return administratorRepo.findAll();
    }

    public Optional<Administrator> getAdministratorById(Long id) {
        return administratorRepo.findById(id);
    }

    public void addAdministrator(Administrator administrator) {
    	loginServ.addPermsion(administrator.getAccountData(), "ROLE_ADMIN");
        administratorRepo.save(administrator);
    }

    public void removeAdministrator(Long id) {
        Optional<Administrator> administrator = administratorRepo.findById(id);
        administratorRepo.delete(administrator.get());
    }

    public void updateAdministrator(Long id, Administrator administrator) {
        Optional<Administrator> Adm = administratorRepo.findById(id);
        if(Adm.isPresent()) {
            administrator.setId(Adm.get().getId());
            administratorRepo.save(administrator);
        }
    }

}
