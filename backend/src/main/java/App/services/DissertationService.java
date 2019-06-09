package App.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.repositories.DissertationRepository;

@Service
public class DissertationService {
	
	@Autowired
	DissertationRepository dissRepo;
	
	public ArrayList<Object> findDissertationByStudentId(Long studentId){
		return dissRepo.findByStudentYearStudentId(studentId);
	}

}
