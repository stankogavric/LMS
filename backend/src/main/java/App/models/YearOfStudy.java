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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonView;

import App.utils.View.ShowSubjectRealization;


@Entity
public class YearOfStudy {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private int year;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date endDate;

	@JsonView(ShowSubjectRealization.class)
	@OneToMany(mappedBy="yearOfStudy")
	private Set<SubjectRealization> subjectRealizations;

	//@JsonView(ShowStudyProgram.class)
	@ManyToOne(cascade=CascadeType.ALL)
	private StudyProgram studyProgram;

	public YearOfStudy() {}

	public YearOfStudy(int year, Date startDate, Date endDate, Set<SubjectRealization> subjectRealizations, StudyProgram studyProgram) {
		this.year = year;
		this.startDate = startDate;
		this.endDate = endDate;
		this.subjectRealizations = subjectRealizations;
		this.studyProgram = studyProgram;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}
		
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Set<SubjectRealization> getSubjectRealizations(){
		return subjectRealizations;
	}

	public void setSubjectRealizations(Set<SubjectRealization> subjectRealizations){
		this.subjectRealizations = subjectRealizations;
	}
	
	public StudyProgram getStudyProgram(){
		return studyProgram;
	}

	public void setStudyProgram(StudyProgram studyProgram){
		this.studyProgram = studyProgram;
	}
	
}