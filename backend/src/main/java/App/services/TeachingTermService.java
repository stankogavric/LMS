package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.TeacherRealization;
import App.models.TeachingTerm;
import App.repositories.TeacherRealizationRepository;
import App.repositories.TeachingTermRepository;

@Service
public class TeachingTermService {

	@Autowired
    private TeachingTermRepository teachingTermRepo;
	@Autowired
	private TeacherRealizationRepository teacherRealizationRepo;

    public TeachingTermService() {
    }

    public Iterable<TeachingTerm> getTeachingTerms() {
        return teachingTermRepo.findAll();
    }
    
    public Iterable<Optional<TeachingTerm>> getTeachingTermsByYearOfStudy(Long yearOfStudyId) {
        return teachingTermRepo.getByYearOfStudy(yearOfStudyId);
    }

    public Optional<TeachingTerm> getTeachingTermById(Long id) {
        return teachingTermRepo.findById(id);
    }

    public void addTeachingTerm(TeachingTerm teachingTerm) {
    	if(checkTeachingTerm(teachingTerm)) {
    		teachingTermRepo.save(teachingTerm);
    	}
    }
    
    public void removeTeachingTerm(Long id) {
        Optional<TeachingTerm> teachingTerm = teachingTermRepo.findById(id);
        TeachingTerm t = teachingTerm.get();
        t.setDeleted(true);
        teachingTermRepo.save(t);
    }

    public void updateTeachingTerm(Long id, TeachingTerm teachingTerm) {
    	if(checkTeachingTerm(teachingTerm)) {
	        Optional<TeachingTerm> Tea = teachingTermRepo.findById(id);
	        if(Tea.isPresent()) {
	            teachingTerm.setId(Tea.get().getId());
	            teachingTermRepo.save(teachingTerm);
	        }
    	}
    }
    
    private Boolean checkTeachingTerm(TeachingTerm teachingTerm) {
    	Iterable<TeacherRealization> teacherRealizations = teacherRealizationRepo.findAll();
    	Iterable<TeachingTerm> teachingTerms = teachingTermRepo.findAll();
    	Boolean teacherFree = true;
    	for(TeacherRealization tr: teacherRealizations) {
    		if(//teacherRealization.teacher.id == item.teacherRealization.teacher.id &&
    			tr.getSubjectRealization().getId() == teachingTerm.getSubjectRealization().getId()) {
	    			teacherFree = false;
	    				break;
    		}
    	}
    	for(TeachingTerm t : teachingTerms) {
    		if(
    			(
	    			!teacherFree
	    			|| 
	    			t.getClassroom().getId() == teachingTerm.getClassroom().getId()
	    			|| 
	    			t.getSubjectRealization().getYearOfStudy().getId() == teachingTerm.getSubjectRealization().getYearOfStudy().getId()
    			)
    			&&
    			(
	    			t.getDay() == teachingTerm.getDay()
	    			&& 
	    			(
	    				(
	    					t.getStartTime().getTime() > teachingTerm.getStartTime().getTime()
		    				&&
		    				t.getStartTime().getTime() < teachingTerm.getEndTime().getTime()
		    			)
	    				||
	    				(
	    					t.getEndTime().getTime() > teachingTerm.getStartTime().getTime()
	    					&&
	    					t.getEndTime().getTime() < teachingTerm.getEndTime().getTime()
	    				)
	    				||
	    				(
	    					t.getStartTime().getTime() < teachingTerm.getStartTime().getTime()
	    					&&
	    					t.getEndTime().getTime() > teachingTerm.getEndTime().getTime()
	    				)
	    				||
	    				(
	    					t.getStartTime().getTime() == teachingTerm.getStartTime().getTime()
	    					&&
	    					t.getEndTime().getTime() == teachingTerm.getEndTime().getTime()
	    				)
	    			)
    			)
    		) {
    			return false;
    		}
    	}
    	return true;
    }

}
