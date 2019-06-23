package App.dto;

public class StudentExamRegistrationDTO {
	private String firstName, lastName, indexNum;
	private Long studentId, subjectRealizationId, examRealizationId, subjectAttendanceId, studentYearId;
	private int test1, test2, attendance, finalExam, grade;
	
	
	public int getTest1() {
		return test1;
	}
	public void setTest1(int test1) {
		this.test1 = test1;
	}
	public int getTest2() {
		return test2;
	}
	public void setTest2(int test2) {
		this.test2 = test2;
	}
	public int getAttendance() {
		return attendance;
	}
	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}
	public int getFinalExam() {
		return finalExam;
	}
	public void setFinalExam(int finalExam) {
		this.finalExam = finalExam;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public Long getStudentYearId() {
		return studentYearId;
	}
	public void setStudentYearId(Long studentYearId) {
		this.studentYearId = studentYearId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getIndexNum() {
		return indexNum;
	}
	public void setIndexNum(String indexNum) {
		this.indexNum = indexNum;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Long getSubjectRealizationId() {
		return subjectRealizationId;
	}
	public void setSubjectRealizationId(Long subjectRealizationId) {
		this.subjectRealizationId = subjectRealizationId;
	}
	public Long getExamRealizationId() {
		return examRealizationId;
	}
	public void setExamRealizationId(Long examRealizationId) {
		this.examRealizationId = examRealizationId;
	}
	public Long getSubjectAttendanceId() {
		return subjectAttendanceId;
	}
	public void setSubjectAttendanceId(Long subjectAttendanceId) {
		this.subjectAttendanceId = subjectAttendanceId;
	}
	
	public StudentExamRegistrationDTO(String firstName, String lastName, String indexNum, Long studentId,
			Long subjectRealizationId, Long examRealizationId, Long subjectAttendanceId, Long studentYearId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.indexNum = indexNum;
		this.studentId = studentId;
		this.subjectRealizationId = subjectRealizationId;
		this.examRealizationId = examRealizationId;
		this.subjectAttendanceId = subjectAttendanceId;
		this.studentYearId = studentYearId;
	}
	public StudentExamRegistrationDTO() {}
	@Override
	public String toString() {
		return "StudentExamRegistrationDTO [firstName=" + firstName + ", lastName=" + lastName + ", indexNum="
				+ indexNum + ", studentId=" + studentId + ", subjectRealizationId=" + subjectRealizationId
				+ ", examRealizationId=" + examRealizationId + ", subjectAttendanceId=" + subjectAttendanceId
				+ ", studentYearId=" + studentYearId + ", test1=" + test1 + ", test2=" + test2 + ", attendance="
				+ attendance + ", finalExam=" + finalExam + ", grade=" + grade + "]";
	}

	
}
