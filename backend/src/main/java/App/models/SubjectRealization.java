package App.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

import App.utils.View.ShowExam;
import App.utils.View.ShowTeacherRealization;
import App.utils.View.ShowTeachingMaterial;
import App.utils.View.ShowTeachingTerm;


@Entity
public class SubjectRealization {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@JsonView(ShowTeacherRealization.class)
	@OneToMany(mappedBy="subjectRealization")
	private Set<TeacherRealization> teacherRealizations;
	
	@JsonView(ShowTeachingTerm.class)
	@OneToMany(mappedBy="subjectRealization")
	private Set<TeachingTerm> teachingTerms;
	
	@JsonView(ShowExam.class)
	@OneToMany(mappedBy="subjectRealization")
	private Set<Exam> exams;

	@ManyToOne(cascade=CascadeType.ALL)
	private Subject subject;
	
	@JsonView(ShowTeachingMaterial.class)
	@OneToMany(mappedBy="subjectRealization")
	private Set<TeachingMaterial> teachingMaterials;

	@ManyToOne(cascade=CascadeType.ALL)
	private YearOfStudy yearOfStudy;

	public SubjectRealization() {}

	public SubjectRealization(Set<TeacherRealization> teacherRealizations, Set<TeachingTerm> teachingTerms,
			Set<Exam> exams, Subject subject, Set<TeachingMaterial> teachingMaterials, YearOfStudy yearOfStudy) {
		super();
		this.teacherRealizations = teacherRealizations;
		this.teachingTerms = teachingTerms;
		this.exams = exams;
		this.subject = subject;
		this.teachingMaterials = teachingMaterials;
		this.yearOfStudy = yearOfStudy;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public Set<TeacherRealization> getTeacherRealizations(){
		return teacherRealizations;
	}

	public void setTeacherRealizations(Set<TeacherRealization> teacherRealizations){
		this.teacherRealizations = teacherRealizations;
	}
	
	public Subject getSubject(){
		return subject;
	}

	public void setSubject(Subject subject){
		this.subject = subject;
	}

	public Set<TeachingTerm> getTeachingTerms() {
		return teachingTerms;
	}

	public void setTeachingTerms(Set<TeachingTerm> teachingTerms) {
		this.teachingTerms = teachingTerms;
	}

	public Set<Exam> getExams() {
		return exams;
	}

	public void setExams(Set<Exam> exams) {
		this.exams = exams;
	}

	public Set<TeachingMaterial> getTeachingMaterials() {
		return teachingMaterials;
	}

	public void setTeachingMaterials(Set<TeachingMaterial> teachingMaterials) {
		this.teachingMaterials = teachingMaterials;
	}

	public YearOfStudy getYearOfStudy() {
		return yearOfStudy;
	}

	public void setYearOfStudy(YearOfStudy yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}
	
}