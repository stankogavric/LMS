package App.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

import App.utils.View.ShowExamRealization;
import App.utils.View.ShowTopic;

@Entity
public class Exam {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Date startTime;
	
	@Column
	private Date endTime;
	
	@Column(nullable = false)
	private Integer points;
	
	@Column(nullable = false)
	private Integer durationInMinutes;
	
	@ManyToOne(cascade={CascadeType.REFRESH, CascadeType.MERGE})
	private SubjectRealization subjectRealization;
	
	@ManyToOne(cascade={CascadeType.REFRESH, CascadeType.MERGE})
	private ExamType examType;
	
	@JsonView(ShowExamRealization.class)
	@OneToMany(mappedBy="exam")
	private Set<ExamRealization> examRealizations;
	
	@JsonView(ShowTopic.class)
	@OneToMany(mappedBy="exam", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ExamTopic> syllabus;

	public Exam() {
		super();
	}

	public Exam(Date startTime, Date endTime, Integer points, Integer durationInMinutes,
			SubjectRealization subjectRealization, ExamType examType, Set<ExamRealization> examRealizations,
			Set<ExamTopic> syllabus) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.points = points;
		this.durationInMinutes = durationInMinutes;
		this.subjectRealization = subjectRealization;
		this.examType = examType;
		this.examRealizations = examRealizations;
		this.syllabus = syllabus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public SubjectRealization getSubjectRealization() {
		return subjectRealization;
	}

	public void setSubjectRealization(SubjectRealization subjectRealization) {
		this.subjectRealization = subjectRealization;
	}

	public ExamType getExamType() {
		return examType;
	}

	public void setExamType(ExamType examType) {
		this.examType = examType;
	}

	public Set<ExamRealization> getExamRealizations() {
		return examRealizations;
	}

	public void setExamRealizations(Set<ExamRealization> examRealizations) {
		this.examRealizations = examRealizations;
	}

	public Set<ExamTopic> getSyllabus() {
		return syllabus;
	}

	public void setSyllabus(Set<ExamTopic> syllabus) {
		this.syllabus = syllabus;
	}

	public Integer getDurationInMinutes() {
		return durationInMinutes;
	}

	public void setDurationInMinutes(Integer durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}
	
}
