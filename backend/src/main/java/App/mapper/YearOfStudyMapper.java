package App.mapper;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import App.dto.YearOfStudyDTO;
import App.models.YearOfStudy;

@Component
public class YearOfStudyMapper implements Mapper<YearOfStudy, YearOfStudyDTO>{

	@Override
	public YearOfStudyDTO toDTO(YearOfStudy e) {
		if (e==null) return null;
		
		YearOfStudyDTO retVal = new YearOfStudyDTO();
		retVal.setId(e.getId());
		retVal.setStartDate(e.getStartDate());
		retVal.setEndDate(e.getEndDate());
		retVal.setYear(e.getYear());
		retVal.setStudyProgramName(e.getStudyProgram().getName());
		retVal.setFacultyName(e.getStudyProgram().getFaculty().getName());
		
		return retVal;
	}

	@Override
	public YearOfStudy toEntity(YearOfStudyDTO edto) {
		return null;
	}

	@Override
	public Collection<YearOfStudyDTO> toDTO(Collection<YearOfStudy> es) {
		if (es == null) return null;
		
		Collection<YearOfStudyDTO> ys = new ArrayList<YearOfStudyDTO>();
		for (YearOfStudy y : es) {
			ys.add(toDTO(y));			
		}		
		return ys;
	}

	@Override
	public Collection<YearOfStudy> toEntityList(Collection<YearOfStudyDTO> edtos) {
		return null;
	}

}
