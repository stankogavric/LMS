package App.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonView;

import App.utils.View.ShowSubject;
import App.utils.View.ShowTopic;
import App.utils.View.ShowYearOfStudy;


@Entity
@Where(clause = "deleted = 'false'")
public class Subject {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(length=128, nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer ects;

	@Column(nullable = false)
	private Boolean mandatory;

	@Column(nullable = false)
	private Integer lecturesNum;

	@Column(nullable = false)
	private Integer exercisesNum;

	@Column(nullable = false)
	private Integer otherActivitiesNum;

	@Column(nullable = false)
	private Integer researchPaper;

	@Column(nullable = false)
	private Integer otherClasses;

	@JsonView(ShowTopic.class)
	@OneToMany(mappedBy="subject")
	private Set<Topic> syllabus;

//	@JsonView(ShowPrerequisite.class)
//	@OneToMany(mappedBy="subject")
//	private Set<Prerequisite> prerequisites;
	
	@ManyToMany
    private Set<Subject> prerequisites;
	
	@JsonView(ShowSubject.class)
	@ManyToMany(mappedBy="prerequisites")
    private Set<Subject> prerequisiteFor;

	@JsonView(ShowYearOfStudy.class)
	@ManyToOne(cascade=CascadeType.ALL)
	private YearOfStudy yearOfStudy;
	
	@NotNull
	private Boolean deleted = false;

	public Subject() {}

	public Subject(String name, Integer ects, Boolean mandatory, Integer lecturesNum, Integer exercisesNum,
			Integer otherActivitiesNum, Integer researchPaper, Integer otherClasses, Set<Topic> syllabus,
			Set<Subject> prerequisites, Set<Subject> prerequisiteFor, YearOfStudy yearOfStudy,
			Boolean deleted) {
		super();
		this.name = name;
		this.ects = ects;
		this.mandatory = mandatory;
		this.lecturesNum = lecturesNum;
		this.exercisesNum = exercisesNum;
		this.otherActivitiesNum = otherActivitiesNum;
		this.researchPaper = researchPaper;
		this.otherClasses = otherClasses;
		this.syllabus = syllabus;
		this.prerequisites = prerequisites;
		this.prerequisiteFor = prerequisiteFor;
		this.yearOfStudy = yearOfStudy;
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
	
	public Integer getEcts(){
		return ects;
	}

	public void setEcts(Integer ects){
		this.ects = ects;
	}
	
	public Boolean getMandatory(){
		return mandatory;
	}

	public void setMandatory(Boolean mandatory){
		this.mandatory = mandatory;
	}
	
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
	
	public Integer getOtherActivitiesNum() {
		return otherActivitiesNum;
	}

	public void setOtherActivitiesNum(Integer otherActivitiesNum) {
		this.otherActivitiesNum = otherActivitiesNum;
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
	
	public Set<Topic> getSyllabus(){
		return syllabus;
	}

	public void setSyllabus(Set<Topic> syllabus){
		this.syllabus = syllabus;
	}

	public Set<Subject> getPrerequisites() {
		return prerequisites;
	}

	public void setPrerequisites(Set<Subject> prerequisites) {
		this.prerequisites = prerequisites;
	}

	public Set<Subject> getPrerequisiteFor() {
		return prerequisiteFor;
	}

	public void setPrerequisiteFor(Set<Subject> prerequisiteFor) {
		this.prerequisiteFor = prerequisiteFor;
	}

	public YearOfStudy getYearOfStudy(){
		return yearOfStudy;
	}

	public void setYearOfStudy(YearOfStudy yearOfStudy){
		this.yearOfStudy = yearOfStudy;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
}