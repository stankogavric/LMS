package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.StudyProgram;
import App.repositories.StudyProgramRepository;

@Service
public class StudyProgramService {

    @Autowired
    private StudyProgramRepository studyProgramRepo;

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
        studyProgramRepo.delete(studyProgram.get());
    }

    public void updateStudyProgram(Long id, StudyProgram studyProgram) {
        Optional<StudyProgram> Stu = studyProgramRepo.findById(id);
        if(Stu.isPresent()) {
            studyProgram.setId(Stu.get().getId());
            studyProgramRepo.save(studyProgram);
        }
    }

}
