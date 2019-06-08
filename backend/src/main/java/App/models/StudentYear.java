package App.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;

import App.utils.View.ShowExamRealization;


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
	
	@JsonView(ShowExamRealization.class)
	@OneToMany(mappedBy="studentYear")
	private Set<ExamRealization> examRealizations;
	
	@OneToOne(mappedBy = "studentYear")
	private Dissertation dissertation;

	public StudentYear() {}

	public StudentYear(Date enrolmentDate, String numIndex, YearOfStudy yearOfStudy, Student student,
			Set<ExamRealization> examRealizations, Dissertation dissertation) {
		super();
		this.enrolmentDate = enrolmentDate;
		this.numIndex = numIndex;
		this.yearOfStudy = yearOfStudy;
		this.student = student;
		this.examRealizations = examRealizations;
		this.dissertation = dissertation;
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

	public Set<ExamRealization> getExamRealizations() {
		return examRealizations;
	}

	public void setExamRealizations(Set<ExamRealization> examRealizations) {
		this.examRealizations = examRealizations;
	}

	public Dissertation getDissertation() {
		return dissertation;
	}

	public void setDissertation(Dissertation dissertation) {
		this.dissertation = dissertation;
	}
	
	
	
}