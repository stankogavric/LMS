package App.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import java.util.Date;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.OneToMany;
import App.utils.View.ShowFaculty;
import javax.persistence.ManyToOne;


@Entity
public class University {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(length=128, nullable = false)
	private String name;

	@Column(nullable = false)
	private Date dateOfEstablishment;

	@JsonView(ShowFaculty.class)
	@OneToMany(mappedBy="university")
	private Set<Faculty> faculties;

	@ManyToOne(cascade=CascadeType.ALL)
	private Address address;

	@ManyToOne(cascade=CascadeType.ALL)
	private Teacher rector;

	public University() {}

	public University(String name, Date dateOfEstablishment, Set<Faculty> faculties, Address address, Teacher rector){
		this.name = name;
		this.dateOfEstablishment = dateOfEstablishment;
		this.faculties = faculties;
		this.address = address;
		this.rector = rector;
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
	
	public Date getDateOfEstablishment(){
		return dateOfEstablishment;
	}

	public void setDateOfEstablishment(Date dateOfEstablishment){
		this.dateOfEstablishment = dateOfEstablishment;
	}
	
	public Set<Faculty> getFaculties(){
		return faculties;
	}

	public void setFaculties(Set<Faculty> faculties){
		this.faculties = faculties;
	}
	
	public Address getAddress(){
		return address;
	}

	public void setAddress(Address address){
		this.address = address;
	}
	
	public Teacher getRector(){
		return rector;
	}

	public void setRector(Teacher rector){
		this.rector = rector;
	}
	
}