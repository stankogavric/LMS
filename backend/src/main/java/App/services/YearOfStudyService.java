package App.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.Subject;
import App.models.YearOfStudy;
import App.repositories.SubjectRepository;
import App.repositories.YearOfStudyRepository;

@Service
public class YearOfStudyService {

    @Autowired
    private YearOfStudyRepository yearOfStudyRepo;
    @Autowired
    private SubjectRepository subjectRepo;
    @Autowired
    private YearOfStudyService yearOfStudyService;

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

    public ArrayList<Subject> getSubjects(Long yearOfStudyId) {
    	return subjectRepo.findByYearOfStudyIdEquals(yearOfStudyId);
    }
    
    public Optional<YearOfStudy> getNextYearOfStudyByStudyProgram(Long id) {
    	Optional<YearOfStudy> yos = yearOfStudyService.getYearOfStudyById(id);
    	if(yos.isPresent()) {
    		YearOfStudy yearOfStudy = yos.get();
    		int year = yearOfStudy.getYear();
    		year = year+1;
    		Optional<YearOfStudy> nextYearOfStudy = yearOfStudyRepo.getNextYearOfStudy(year, yearOfStudy.getStudyProgram().getId());
    		return nextYearOfStudy;
    	}
        return yos;
    }
}
