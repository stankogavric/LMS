package App.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;


@Entity
public class Topic {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(length=128, nullable = false)
	private String description;

	@ManyToOne(cascade=CascadeType.ALL)
	private Subject subject;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private TeachingTerm teachingTerm;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Exam exam;

	public Topic() {}

	public Topic(String description, Subject subject, TeachingTerm teachingTerm, Exam exam) {
		super();
		this.description = description;
		this.subject = subject;
		this.teachingTerm = teachingTerm;
		this.exam = exam;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}
	
	public Subject getSubject(){
		return subject;
	}

	public void setSubject(Subject subject){
		this.subject = subject;
	}

	public TeachingTerm getTeachingTerm() {
		return teachingTerm;
	}

	public void setTeachingTerm(TeachingTerm teachingTerm) {
		this.teachingTerm = teachingTerm;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}
	
}