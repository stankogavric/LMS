package App.dto;

import java.util.Date;

public class YearOfStudyDTO {
	
	private Long id;
	private int year;
	private Date startDate, endDate;
	private String studyProgramName;
	private String facultyName;
	
	public String getFacultyName() {
		return facultyName;
	}
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStudyProgramName() {
		return studyProgramName;
	}
	public void setStudyProgramName(String studyProgramName) {
		this.studyProgramName = studyProgramName;
	}
	
	public YearOfStudyDTO(Long id, int year, Date startDate, Date endDate, String studyProgramName,
			String facultyName) {
		super();
		this.id = id;
		this.year = year;
		this.startDate = startDate;
		this.endDate = endDate;
		this.studyProgramName = studyProgramName;
		this.facultyName = facultyName;
	}
	public YearOfStudyDTO() {}

}
