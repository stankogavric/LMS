package App.mapper;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import App.dto.StudentYearDTO;
import App.models.StudentYear;

@Component
public class StudentYearMapper implements Mapper<StudentYear, StudentYearDTO>{

	@Autowired
	YearOfStudyMapper ysMapper;
	
	@Override
	public StudentYearDTO toDTO(StudentYear e) {
		if(e == null) return null;
		StudentYearDTO retVal = new StudentYearDTO();
		retVal.setNumIndex(e.getNumIndex());
		retVal.setEnrolmentDate(e.getEnrolmentDate());
		retVal.setYearOfStudy(ysMapper.toDTO(e.getYearOfStudy()));
		return retVal;
	}

	@Override
	public StudentYear toEntity(StudentYearDTO edto) {
		return null;
	}

	@Override
	public Collection<StudentYearDTO> toDTO(Collection<StudentYear> es) {
		if (es.size() == 0) return null;
		Collection<StudentYearDTO> years = new ArrayList<StudentYearDTO>(es.size());
		for (StudentYear e : es) {
			years.add(toDTO(e));			
		}		
		return years;
	}

	@Override
	public Collection<StudentYear> toEntityList(Collection<StudentYearDTO> edtos) {
		return null;
	}

}
