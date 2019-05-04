package App.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.StudyProgram;
import App.models.Subject;
import App.models.YearOfStudy;
import App.repositories.StudyProgramRepository;
import App.repositories.YearOfStudyRepository;

@Service
public class StudyProgramService {

    @Autowired
    private StudyProgramRepository studyProgramRepo;
    @Autowired
    private YearOfStudyRepository yearOfStudyRepo;

    public StudyProgramService() {
    }

    public Iterable<StudyProgram> getStudyPrograms() {
        return studyProgramRepo.findAll();
    }

    public Optional<StudyProgram> getStudyProgramById(Long id) {
        return studyProgramRepo.findById(id);
    }

    public void addStudyProgram(StudyProgram studyProgram) {
        studyProgramRepo.save(studyProgram);
    }
    
    public void removeStudyProgram(Long id) {
        Optional<StudyProgram> studyProgram = studyProgramRepo.findById(id);
        StudyProgram s = studyProgram.get();
        s.setDeleted(true);
        studyProgramRepo.save(s);
    }

    public void updateStudyProgram(Long id, StudyProgram studyProgram) {
        Optional<StudyProgram> Stu = studyProgramRepo.findById(id);
        if(Stu.isPresent()) {
            studyProgram.setId(Stu.get().getId());
            studyProgramRepo.save(studyProgram);
        }
    }
    
    public Iterable<Subject> getSubjectsCandidatedForRemoval(Long studyProgramId) {
        return studyProgramRepo.findSubjectsCandidatedForRemoval(studyProgramId);
    }
    
    public ArrayList<YearOfStudy> getYearsOfStudy(Long studyProgramId) {
    	return yearOfStudyRepo.findByStudyProgramIdEquals(studyProgramId);
    }

}
