package App.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ExamRealization {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = true)
	private Integer points;
	
	@Column(length=128, nullable = true)
	private String note;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Exam exam;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private StudentYear studentYear;

	public ExamRealization() {
		super();
	}

	public ExamRealization(Integer points, String note, Exam exam, StudentYear studentYear) {
		super();
		this.points = points;
		this.note = note;
		this.exam = exam;
		this.studentYear = studentYear;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public StudentYear getStudentYear() {
		return studentYear;
	}

	public void setStudentYear(StudentYear studentYear) {
		this.studentYear = studentYear;
	}
	
}
