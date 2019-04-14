package App.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Set;
import App.utils.View.ShowSubject;
import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;


@Entity
public class YearOfStudy {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Date year;

	@JsonView(ShowSubject.class)
	@OneToMany(mappedBy="None")
	private Set<Subject> subjects;

	@ManyToOne(cascade=CascadeType.ALL)
	private StudyProgram studyProgram;

	public YearOfStudy() {}

	public YearOfStudy(Date year, Set<Subject> subjects, StudyProgram studyProgram){
		this.year = year;
		this.subjects = subjects;
		this.studyProgram = studyProgram;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public Date getYear(){
		return year;
	}

	public void setYear(Date year){
		this.year = year;
	}
	
	public Set<Subject> getSubjects(){
		return subjects;
	}

	public void setSubjects(Set<Subject> subjects){
		this.subjects = subjects;
	}
	
	public StudyProgram getStudyProgram(){
		return studyProgram;
	}

	public void setStudyProgram(StudyProgram studyProgram){
		this.studyProgram = studyProgram;
	}
	
}