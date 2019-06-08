package App.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Dissertation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=128, nullable = false)
	private String title;
	
	@Column(length=128, nullable = false)
	@Temporal(value = TemporalType.DATE)
	private Date applicationDate;
	
	@Column(length=128, nullable = false)
	@Temporal(value = TemporalType.DATE)
	private Date defenseDate;
	
	@ManyToOne(cascade= {CascadeType.REFRESH, CascadeType.MERGE})
	private Teacher mentor;
	
	@OneToOne(fetch = FetchType.LAZY)
	private StudentYear studentYear;
	
	@OneToOne(fetch = FetchType.EAGER)
	private DissertationFile file;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public Date getDefenseDate() {
		return defenseDate;
	}

	public void setDefenseDate(Date defenseDate) {
		this.defenseDate = defenseDate;
	}

	public Teacher getMentor() {
		return mentor;
	}

	public void setMentor(Teacher mentor) {
		this.mentor = mentor;
	}

	public StudentYear getStudentYear() {
		return studentYear;
	}

	public void setStudentYear(StudentYear studentYear) {
		this.studentYear = studentYear;
	}

	public DissertationFile getFile() {
		return file;
	}

	public void setFile(DissertationFile file) {
		this.file = file;
	}

	public Dissertation(String title, Date applicationDate, Date defenseDate, Teacher mentor,
			StudentYear studentYear, DissertationFile file) {
		super();
		this.title = title;
		this.applicationDate = applicationDate;
		this.defenseDate = defenseDate;
		this.mentor = mentor;
		this.studentYear = studentYear;
		this.file = file;
	}
	
	public Dissertation() {}

}
