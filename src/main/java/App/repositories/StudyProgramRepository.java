package App.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import App.models.StudyProgram;

@Repository
public interface StudyProgramRepository extends CrudRepository<StudyProgram, Long> {

}