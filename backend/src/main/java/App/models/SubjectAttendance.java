package App.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;


@Entity
public class SubjectAttendance {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = true)
	private Integer finalGrade;

	@ManyToOne(cascade=CascadeType.ALL)
	private Student student;

	@ManyToOne(cascade=CascadeType.ALL)
	private SubjectRealization subjectRealization;

	public SubjectAttendance() {}

	public SubjectAttendance(Integer finalGrade, Student student, SubjectRealization subjectRealization){
		this.finalGrade = finalGrade;
		this.student = student;
		this.subjectRealization = subjectRealization;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public Integer getFinalGrade(){
		return finalGrade;
	}

	public void setFinalGrade(Integer finalGrade){
		this.finalGrade = finalGrade;
	}
	
	public Student getStudent(){
		return student;
	}

	public void setStudent(Student student){
		this.student = student;
	}
	
	public SubjectRealization getSubjectRealization(){
		return subjectRealization;
	}

	public void setSubjectRealization(SubjectRealization subjectRealization){
		this.subjectRealization = subjectRealization;
	}
	
}