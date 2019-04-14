package App.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import java.util.Date;


@Entity
public class StudentYear {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Date enrolmentDate;

	@Column(length=128, nullable = false)
	private String numIndex;

	@ManyToOne(cascade=CascadeType.ALL)
	private YearOfStudy yearOfStudy;

	@ManyToOne(cascade=CascadeType.ALL)
	private Student student;

	public StudentYear() {}

	public StudentYear(Date enrolmentDate, String numIndex, YearOfStudy yearOfStudy, Student student){
		this.enrolmentDate = enrolmentDate;
		this.numIndex = numIndex;
		this.yearOfStudy = yearOfStudy;
		this.student = student;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public Date getEnrolmentDate(){
		return enrolmentDate;
	}

	public void setEnrolmentDate(Date enrolmentDate){
		this.enrolmentDate = enrolmentDate;
	}
	
	public String getNumIndex(){
		return numIndex;
	}

	public void setNumIndex(String numIndex){
		this.numIndex = numIndex;
	}
	
	public YearOfStudy getYearOfStudy(){
		return yearOfStudy;
	}

	public void setYearOfStudy(YearOfStudy yearOfStudy){
		this.yearOfStudy = yearOfStudy;
	}
	
	public Student getStudent(){
		return student;
	}

	public void setStudent(Student student){
		this.student = student;
	}
	
}