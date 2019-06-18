package App.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ElectiveSubjectAttendance {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade=CascadeType.ALL)
	private Student student;

	@ManyToOne(cascade=CascadeType.ALL)
	private SubjectRealization subjectRealization;

	public ElectiveSubjectAttendance() {}

	public ElectiveSubjectAttendance(Student student, SubjectRealization subjectRealization){
		this.student = student;
		this.subjectRealization = subjectRealization;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
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