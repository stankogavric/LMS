package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.RegisteredUser;
import App.repositories.RegisteredUserRepository;

@Service
public class RegisteredUserService {

    @Autowired
    private RegisteredUserRepository registeredUserRepo;

    public RegisteredUserService() {
    }

    public Iterable<RegisteredUser> getRegisteredUsers() {
        return registeredUserRepo.findAll();
    }

    public Optional<RegisteredUser> getRegisteredUserById(Long id) {
        return registeredUserRepo.findById(id);
    }

    public void addRegisteredUser(RegisteredUser registeredUser) {
        registeredUserRepo.save(registeredUser);
    }

    public void removeRegisteredUser(Long id) {
        Optional<RegisteredUser> registeredUser = registeredUserRepo.findById(id);
        registeredUserRepo.delete(registeredUser.get());
    }

    public void updateRegisteredUser(Long id, RegisteredUser registeredUser) {
        Optional<RegisteredUser> Reg = registeredUserRepo.findById(id);
        if(Reg.isPresent()) {
            registeredUser.setId(Reg.get().getId());
            registeredUserRepo.save(registeredUser);
        }
    }

}
