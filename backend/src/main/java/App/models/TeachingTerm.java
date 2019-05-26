package App.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TeachingTerm {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Date startTime;
	
	@Column(nullable = false)
	private Date endTime;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private SubjectRealization subjectRealization;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Classroom classroom;

	public TeachingTerm() {
		super();
	}

	public TeachingTerm(Date startTime, Date endTime, SubjectRealization subjectRealization, Classroom classroom) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.subjectRealization = subjectRealization;
		this.classroom = classroom;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public SubjectRealization getSubjectRealization() {
		return subjectRealization;
	}

	public void setSubjectRealization(SubjectRealization subjectRealization) {
		this.subjectRealization = subjectRealization;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

}