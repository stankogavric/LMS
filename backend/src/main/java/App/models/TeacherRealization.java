package App.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;


@Entity
public class TeacherRealization {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Integer numberOfClasses;

	@ManyToOne(cascade=CascadeType.ALL)
	private TeachingType teachingType;

	@ManyToOne(cascade=CascadeType.ALL)
	private Teacher teacher;

	@ManyToOne(cascade=CascadeType.ALL)
	private SubjectRealization subjectRealization;

	public TeacherRealization() {}

	public TeacherRealization(Integer numberOfClasses, TeachingType teachingType, Teacher teacher, SubjectRealization subjectRealization){
		this.numberOfClasses = numberOfClasses;
		this.teachingType = teachingType;
		this.teacher = teacher;
		this.subjectRealization = subjectRealization;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public Integer getNumberOfClasses(){
		return numberOfClasses;
	}

	public void setNumberOfClasses(Integer numberOfClasses){
		this.numberOfClasses = numberOfClasses;
	}
	
	public TeachingType getTeachingType(){
		return teachingType;
	}

	public void setTeachingType(TeachingType teachingType){
		this.teachingType = teachingType;
	}
	
	public Teacher getTeacher(){
		return teacher;
	}

	public void setTeacher(Teacher teacher){
		this.teacher = teacher;
	}
	
	public SubjectRealization getSubjectRealization(){
		return subjectRealization;
	}

	public void setSubjectRealization(SubjectRealization subjectRealization){
		this.subjectRealization = subjectRealization;
	}
	
}