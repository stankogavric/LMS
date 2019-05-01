package App.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

import App.utils.View.ShowFaculty;
import App.utils.View.ShowUniversityEmails;
import App.utils.View.ShowUniversityPhones;


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
	
	@Column(length=128, nullable = false)
	private String description;
	
	@JsonView(ShowUniversityPhones.class)
	@OneToMany(mappedBy="university")
	private Set<UniversityPhones> phones;
	
	@JsonView(ShowUniversityEmails.class)
	@OneToMany(mappedBy="university")
	private Set<UniversityEmails> emails;

	public University() {}

	public University(String name, Date dateOfEstablishment, Set<Faculty> faculties, Address address, Teacher rector,
			String description, Set<UniversityPhones> phones, Set<UniversityEmails> emails) {
		super();
		this.name = name;
		this.dateOfEstablishment = dateOfEstablishment;
		this.faculties = faculties;
		this.address = address;
		this.rector = rector;
		this.description = description;
		this.phones = phones;
		this.emails = emails;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<UniversityPhones> getPhones() {
		return phones;
	}

	public void setPhones(Set<UniversityPhones> phones) {
		this.phones = phones;
	}

	public Set<UniversityEmails> getEmails() {
		return emails;
	}

	public void setEmails(Set<UniversityEmails> emails) {
		this.emails = emails;
	}
	
}