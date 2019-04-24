package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.YearOfStudy;
import App.repositories.YearOfStudyRepository;

@Service
public class YearOfStudyService {

    @Autowired
    private YearOfStudyRepository yearOfStudyRepo;

    public YearOfStudyService() {
    }

    public Iterable<YearOfStudy> getYearsOfStudy() {
        return yearOfStudyRepo.findAll();
    }

    public Optional<YearOfStudy> getYearOfStudyById(Long id) {
        return yearOfStudyRepo.findById(id);
    }

    public void addYearOfStudy(YearOfStudy yearOfStudy) {
        yearOfStudyRepo.save(yearOfStudy);
    }

    public void removeYearOfStudy(Long id) {
        Optional<YearOfStudy> yearOfStudy = yearOfStudyRepo.findById(id);
        yearOfStudyRepo.delete(yearOfStudy.get());
    }

    public void updateYearOfStudy(Long id, YearOfStudy yearOfStudy) {
        Optional<YearOfStudy> Yea = yearOfStudyRepo.findById(id);
        if(Yea.isPresent()) {
            yearOfStudy.setId(Yea.get().getId());
            yearOfStudyRepo.save(yearOfStudy);
        }
    }

}
