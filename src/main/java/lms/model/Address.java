package lms.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String ulica;
	private String broj;
	@ManyToMany(mappedBy="addresses")
	private Set<Place> places;
	@OneToOne(mappedBy="address")
	private University university;
	@OneToMany(mappedBy="address")
	private Set<Faculty> faculties;
	
	public Address() {
		super();
	}

	public Address(Long id, String ulica, String broj, Set<Place> places, University university,
			Set<Faculty> faculties) {
		super();
		this.id = id;
		this.ulica = ulica;
		this.broj = broj;
		this.places = places;
		this.university = university;
		this.faculties = faculties;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	public Set<Place> getPlaces() {
		return places;
	}

	public void setPlaces(Set<Place> places) {
		this.places = places;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public Set<Faculty> getFaculties() {
		return faculties;
	}

	public void setFaculties(Set<Faculty> faculties) {
		this.faculties = faculties;
	}
}
