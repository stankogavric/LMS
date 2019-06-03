package App.dto;

import java.util.Date;

public class StudentYearDTO {
	private Date enrolmentDate;
	private String numIndex;
	private YearOfStudyDTO yearOfStudy;
	
	
	public Date getEnrolmentDate() {
		return enrolmentDate;
	}
	public void setEnrolmentDate(Date enrolmentDate) {
		this.enrolmentDate = enrolmentDate;
	}
	public String getNumIndex() {
		return numIndex;
	}
	public void setNumIndex(String numIndex) {
		this.numIndex = numIndex;
	}
	public YearOfStudyDTO getYearOfStudy() {
		return yearOfStudy;
	}
	public void setYearOfStudy(YearOfStudyDTO yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}
	public StudentYearDTO(Date enrolmentDate, String numIndex, YearOfStudyDTO yearOfStudy) {
		
		this.enrolmentDate = enrolmentDate;
		this.numIndex = numIndex;
		this.yearOfStudy = yearOfStudy;
	}
	
	public StudentYearDTO() {}

}
