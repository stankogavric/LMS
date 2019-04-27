package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.AdministrativeStaff;
import App.repositories.AdministrativeStaffRepository;

@Service
public class AdministrativeStaffService {

    @Autowired
    private AdministrativeStaffRepository administrativeStaffRepo;

    public AdministrativeStaffService() {
    }

    public Iterable<AdministrativeStaff> getAdministrativeStaff() {
        return administrativeStaffRepo.findAll();
    }

    public Optional<AdministrativeStaff> getAdministrativeStaffById(Long id) {
        return administrativeStaffRepo.findById(id);
    }

    public void addAdministrativeStaff(AdministrativeStaff administrativeStaff) {
        administrativeStaffRepo.save(administrativeStaff);
    }

    public void removeAdministrativeStaff(Long id) {
        Optional<AdministrativeStaff> administrativeStaff = administrativeStaffRepo.findById(id);
        administrativeStaffRepo.delete(administrativeStaff.get());
    }

    public void updateAdministrativeStaff(Long id, AdministrativeStaff administrativeStaff) {
        Optional<AdministrativeStaff> Adm = administrativeStaffRepo.findById(id);
        if(Adm.isPresent()) {
            administrativeStaff.setId(Adm.get().getId());
            administrativeStaffRepo.save(administrativeStaff);
        }
    }

}
