package App.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;


@Entity
public class Topic {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(length=128, nullable = false)
	private String description;
	
	@Column(nullable = false)
	private Integer week;

	@ManyToOne(cascade= {CascadeType.REFRESH, CascadeType.MERGE})
	private Subject subject;

	public Topic() {}

	public Topic(String description, Integer week, Subject subject) {
		super();
		this.description = description;
		this.week = week;
		this.subject = subject;
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
	
}