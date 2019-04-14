package App.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import java.util.Date;


@Entity
public class Title {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Date startDate;

	@Column(nullable = false)
	private Date endDate;

	@ManyToOne(cascade=CascadeType.ALL)
	private ScientificField scientificField;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Teacher teachers;

	public Title() {}

	public Title(Long id, Date startDate, Date endDate, ScientificField scientificField, Teacher teachers) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.scientificField = scientificField;
		this.teachers = teachers;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public Date getStartDate(){
		return startDate;
	}

	public void setStartDate(Date startDate){
		this.startDate = startDate;
	}
	
	public Date getEndDate(){
		return endDate;
	}

	public void setEndDate(Date endDate){
		this.endDate = endDate;
	}
	
	public ScientificField getScientificField(){
		return scientificField;
	}

	public void setScientificField(ScientificField scientificField){
		this.scientificField = scientificField;
	}

	public Teacher getTeachers() {
		return teachers;
	}

	public void setTeachers(Teacher teachers) {
		this.teachers = teachers;
	}
	
}