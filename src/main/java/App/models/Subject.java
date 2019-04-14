package App.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;
import App.utils.View.ShowSubject;
import App.utils.View.ShowTopic;

import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;


@Entity
public class Subject {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(length=128, nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer ects;

	//@ManyToOne(cascade=CascadeType.ALL)
	//private Boolean mandatory;

	@Column(nullable = false)
	private Integer lecturesNum;

	@Column(nullable = false)
	private Integer exercisesNum;

	@Column(nullable = false)
	private Integer otherActivitesNum;

	@Column(nullable = false)
	private Integer researchPaper;

	@Column(nullable = false)
	private Integer otherClasses;

//	@JsonView(ShowTopic.class)
//	@OneToMany(mappedBy="None")
//	private Set<Topic> syllabus;

//	@JsonView(ShowSubject.class)
//	@OneToMany(mappedBy="None")
//	private Set<Subject> prerequisite;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private YearOfStudy yearOfStudies;

	public Subject() {}

	public Subject(Long id, String name, Integer ects, Integer lecturesNum, Integer exercisesNum,
			Integer otherActivitesNum, Integer researchPaper, Integer otherClasses, /*Set<Topic> syllabus,*/
			/*Set<Subject> prerequisite,*/ YearOfStudy yearOfStudies) {
		super();
		this.id = id;
		this.name = name;
		this.ects = ects;
		this.lecturesNum = lecturesNum;
		this.exercisesNum = exercisesNum;
		this.otherActivitesNum = otherActivitesNum;
		this.researchPaper = researchPaper;
		this.otherClasses = otherClasses;
		//this.syllabus = syllabus;
		//this.prerequisite = prerequisite;
		this.yearOfStudies = yearOfStudies;
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
	
	public Integer getEcts(){
		return ects;
	}

	public void setEcts(Integer ects){
		this.ects = ects;
	}
	
//	public Boolean getMandatory(){
//		return mandatory;
//	}
//
//	public void setMandatory(Boolean mandatory){
//		this.mandatory = mandatory;
//	}
	
	public Integer getLecturesNum(){
		return lecturesNum;
	}

	public void setLecturesNum(Integer lecturesNum){
		this.lecturesNum = lecturesNum;
	}
	
	public Integer getExercisesNum(){
		return exercisesNum;
	}

	public void setExercisesNum(Integer exercisesNum){
		this.exercisesNum = exercisesNum;
	}
	
	public Integer getOtherActivitesNum(){
		return otherActivitesNum;
	}

	public void setOtherActivitesNum(Integer otherActivitesNum){
		this.otherActivitesNum = otherActivitesNum;
	}
	
	public Integer getResearchPaper(){
		return researchPaper;
	}

	public void setResearchPaper(Integer researchPaper){
		this.researchPaper = researchPaper;
	}
	
	public Integer getOtherClasses(){
		return otherClasses;
	}

	public void setOtherClasses(Integer otherClasses){
		this.otherClasses = otherClasses;
	}
	
//	public Set<Topic> getSyllabus(){
//		return syllabus;
//	}
//
//	public void setSyllabus(Set<Topic> syllabus){
//		this.syllabus = syllabus;
//	}
	
//	public Set<Subject> getPrerequisite(){
//		return prerequisite;
//	}
//
//	public void setPrerequisite(Set<Subject> prerequisite){
//		this.prerequisite = prerequisite;
//	}

	public YearOfStudy getYearOfStudies() {
		return yearOfStudies;
	}

	public void setYearOfStudies(YearOfStudy yearOfStudies) {
		this.yearOfStudies = yearOfStudies;
	}
	
	
	
}