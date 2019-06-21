package App.dto;

import java.util.Date;

public class ExamDTO {
	private int grade;
	private String subject;
	private int year;
	private String studyProgramName;
	private int points;
	private Date date;
	private int ects;
	
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getStudyProgramName() {
		return studyProgramName;
	}
	public void setStudyProgramName(String studyProgramName) {
		this.studyProgramName = studyProgramName;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public Date getDate() {
		return date;
	}
	public void setExamDate(Date date) {
		this.date = date;
	}
	public int getEcts() {
		return ects;
	}
	public void setEcts(int ects) {
		this.ects = ects;
	}
	public ExamDTO(int finalGrade, String subjectName, int year, String studyProgramName, int points, Date examDate,
			int ects) {
		super();
		this.grade = finalGrade;
		this.subject = subjectName;
		this.year = year;
		this.studyProgramName = studyProgramName;
		this.points = points;
		this.date = examDate;
		this.ects = ects;
	}
	
	public ExamDTO() {}
	

}
