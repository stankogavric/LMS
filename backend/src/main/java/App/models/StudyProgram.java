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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import java.util.Set;


@Entity
@Where(clause = "deleted = 'false'")
public class StudyProgram {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(length=128, nullable = false)
	private String name;

	@JsonView(ShowYearOfStudy.class)
	@OneToMany(mappedBy="studyProgram")
	private Set<YearOfStudy> yearsOfStudy;

	@ManyToOne(cascade= {CascadeType.REFRESH})
	private Teacher headTeacher;

	@ManyToOne(cascade= {CascadeType.REFRESH})
	private Faculty faculty;
	
	@Column(length=128, nullable = false)
	private String description;
	
	@NotNull
	private Boolean deleted = false;

	public StudyProgram() {}

	public StudyProgram(String name, Set<YearOfStudy> yearsOfStudy, Teacher headTeacher, Faculty faculty,
			String description, Boolean deleted) {
		super();
		this.name = name;
		this.yearsOfStudy = yearsOfStudy;
		this.headTeacher = headTeacher;
		this.faculty = faculty;
		this.description = description;
		this.deleted = deleted;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
}