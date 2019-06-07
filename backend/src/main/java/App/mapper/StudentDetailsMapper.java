package App.mapper;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import App.dto.StudentDetailsDTO;
import App.models.Student;

@Component
public class StudentDetailsMapper implements Mapper<Student, StudentDetailsDTO> {
	
	@Autowired
	StudentYearMapper studentYearMapper;

	@Override
	public StudentDetailsDTO toDTO(Student e) {
		if (e == null) return null;
		
		StudentDetailsDTO retVal = new StudentDetailsDTO();
		retVal.setId(e.getId());
		retVal.setFirstName(e.getPersonalData().getFirstName());
		retVal.setLastName(e.getPersonalData().getLastName());
		retVal.setEmail(e.getAccountData().getEmail());
		retVal.setProfilePicturePath(e.getPersonalData().getProfilePicturePath());
		retVal.setStudentYears(studentYearMapper.toDTO(e.getStudentYears()));
		return retVal;
	}

	@Override
	public Student toEntity(StudentDetailsDTO edto) {
		return null;
	}

	@Override
	public Collection<StudentDetailsDTO> toDTO(Collection<Student> es) {
		if (es.size() == 0) return null;
		Collection<StudentDetailsDTO> students = new ArrayList<StudentDetailsDTO>(es.size());
		for (Student e : es) {
			students.add(toDTO(e));			
		}
		return students;
	}

	@Override
	public Collection<Student> toEntityList(Collection<StudentDetailsDTO> edtos) {
		return null;
	}

}
