package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.TitleType;
import App.repositories.TitleTypeRepository;

@Service
public class TitleTypeService {

    @Autowired
    private TitleTypeRepository titleTypeRepo;

    public TitleTypeService() {
    }

    public Iterable<TitleType> getTitleTypes() {
        return titleTypeRepo.findAll();
    }

    public Optional<TitleType> getTitleTypeById(Long id) {
        return titleTypeRepo.findById(id);
    }

    public void addTitleType(TitleType titleType) {
        titleTypeRepo.save(titleType);
    }

    public void removeTitleType(Long id) {
        Optional<TitleType> titleType = titleTypeRepo.findById(id);
        titleTypeRepo.delete(titleType.get());
    }

    public void updateTitleType(Long id, TitleType titleType) {
        Optional<TitleType> Tit = titleTypeRepo.findById(id);
        if(Tit.isPresent()) {
            titleType.setId(Tit.get().getId());
            titleTypeRepo.save(titleType);
        }
    }

}
