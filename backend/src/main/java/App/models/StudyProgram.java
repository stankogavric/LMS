package App.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.CascadeType;
import App.utils.View.ShowYearOfStudy;
import javax.persistence.OneToMany;
import java.util.Set;


@Entity
public class StudyProgram {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(length=128, nullable = false)
	private String name;

	@JsonView(ShowYearOfStudy.class)
	@OneToMany(mappedBy="studyProgram")
	private Set<YearOfStudy> yearsOfStudy;

	@ManyToOne(cascade=CascadeType.ALL)
	private Teacher headTeacher;

	@ManyToOne(cascade=CascadeType.ALL)
	private Faculty faculty;

	public StudyProgram() {}

	public StudyProgram(String name, Set<YearOfStudy> yearsOfStudy, Teacher headTeacher, Faculty faculty){
		this.name = name;
		this.yearsOfStudy = yearsOfStudy;
		this.headTeacher = headTeacher;
		this.faculty = faculty;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public Set<YearOfStudy> getYearsOfStudy(){
		return yearsOfStudy;
	}

	public void setYearsOfStudy(Set<YearOfStudy> yearsOfStudy){
		this.yearsOfStudy = yearsOfStudy;
	}
	
	public Teacher getHeadTeacher(){
		return headTeacher;
	}

	public void setHeadTeacher(Teacher headTeacher){
		this.headTeacher = headTeacher;
	}
	
	public Faculty getFaculty(){
		return faculty;
	}

	public void setFaculty(Faculty faculty){
		this.faculty = faculty;
	}
	
}