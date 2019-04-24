package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.Title;
import App.repositories.TitleRepository;

@Service
public class TitleService {

    @Autowired
    private TitleRepository titleRepo;

    public TitleService() {
    }

    public Iterable<Title> getTitles() {
        return titleRepo.findAll();
    }

    public Optional<Title> getTitleById(Long id) {
        return titleRepo.findById(id);
    }

    public void addTitle(Title title) {
        titleRepo.save(title);
    }

    public void removeTitle(Long id) {
        Optional<Title> title = titleRepo.findById(id);
        titleRepo.delete(title.get());
    }

    public void updateTitle(Long id, Title title) {
        Optional<Title> Tit = titleRepo.findById(id);
        if(Tit.isPresent()) {
            title.setId(Tit.get().getId());
            titleRepo.save(title);
        }
    }

}
