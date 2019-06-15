package App.dto;

import java.util.Date;

public class ExamRegistrationDTO {
	private Long examId;
	private Long subjectRealizationId;
	private String subjectName;
	private Date startTime;
	private int duration;
	private String examType;
	private int yearOfStudyYear;
	private String studyProgramName;
	public Long getExamId() {
		return examId;
	}
	public void setExamId(Long examId) {
		this.examId = examId;
	}
	public Long getSubjectRealizationId() {
		return subjectRealizationId;
	}
	public void setSubjectRealizationId(Long subjectRealizationId) {
		this.subjectRealizationId = subjectRealizationId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	public int getYearOfStudyYear() {
		return yearOfStudyYear;
	}
	public void setYearOfStudyYear(int yearOfStudyYear) {
		this.yearOfStudyYear = yearOfStudyYear;
	}
	public String getStudyProgramName() {
		return studyProgramName;
	}
	public void setStudyProgramName(String studyProgramName) {
		this.studyProgramName = studyProgramName;
	}
	public ExamRegistrationDTO(Long examId, Long subjectRealizationId, String subjectName, Date startTime, int duration,
			String examType, int yearOfStudyYear, String studyProgramName) {
		super();
		this.examId = examId;
		this.subjectRealizationId = subjectRealizationId;
		this.subjectName = subjectName;
		this.startTime = startTime;
		this.duration = duration;
		this.examType = examType;
		this.yearOfStudyYear = yearOfStudyYear;
		this.studyProgramName = studyProgramName;
	}
	
	public ExamRegistrationDTO() {}

}
