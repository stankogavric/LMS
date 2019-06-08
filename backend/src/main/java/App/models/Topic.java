package App.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;


@Entity
@Where(clause = "deleted = 'false'")
public class Topic {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(length=128, nullable = false)
	private String description;
	
	@Column(nullable = false)
	private Integer week;
	
	@Column(length=128)
	private String iconPath;

	@ManyToOne(cascade= {CascadeType.REFRESH, CascadeType.MERGE})
	private Subject subject;
	
	@NotNull
	private Boolean deleted = false;

	public Topic() {}

	public Topic(String description, Integer week, String iconPath, Subject subject, Boolean deleted) {
		super();
		this.description = description;
		this.week = week;
		this.iconPath = iconPath;
		this.subject = subject;
		this.deleted = deleted;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}
	
	public Subject getSubject(){
		return subject;
	}

	public void setSubject(Subject subject){
		this.subject = subject;
	}

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
}