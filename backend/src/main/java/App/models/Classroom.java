package App.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Classroom {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=128, nullable = false)
	private String name;
	
	@Column(length=128, nullable = false)
	private String type;
	
	@Column(nullable = false)
	private Integer capacity;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Faculty faculty;

	public Classroom() {
		super();
	}

	public Classroom(String name, String type, Integer capacity, Faculty faculty) {
		super();
		this.name = name;
		this.type = type;
		this.capacity = capacity;
		this.faculty = faculty;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
	
}
