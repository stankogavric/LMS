package lms.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Faculty {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String naziv;
	@ManyToOne(cascade=CascadeType.ALL)
	private Address address;
	@ManyToOne(cascade=CascadeType.ALL)
	private University university;
	@OneToMany(mappedBy="faculty")
	private Set<StudyProgram> studyPrograms;
	
	public Faculty() {
		super();
	}

	public Faculty(Long id, String naziv, Address address, University university,
			Set<StudyProgram> studyPrograms) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.address = address;
		this.university = university;
		this.studyPrograms = studyPrograms;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public Set<StudyProgram> getStudyPrograms() {
		return studyPrograms;
	}

	public void setStudyPrograms(Set<StudyProgram> studyPrograms) {
		this.studyPrograms = studyPrograms;
	}
}
