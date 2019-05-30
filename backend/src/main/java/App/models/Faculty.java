package App.models;

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

import App.utils.View.ShowClassroom;
import App.utils.View.ShowFacultyEmails;
import App.utils.View.ShowFacultyPhones;
import App.utils.View.ShowStudyProgram;


@Entity
public class Faculty {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(length=128, nullable = false)
	private String name;

	@ManyToOne(cascade=CascadeType.ALL)
	private University university;

	@ManyToOne(cascade=CascadeType.ALL)
	private Address address;

	@ManyToOne(cascade=CascadeType.ALL)
	private Teacher dean;

	@JsonView(ShowStudyProgram.class)
	@OneToMany(mappedBy="faculty")
	private Set<StudyProgram> studyPrograms;
	
	@Column(length=128, nullable = false)
	private String description;
	
	@JsonView(ShowFacultyPhones.class)
	@OneToMany(mappedBy="faculty")
	private Set<FacultyPhones> phones;
	
	@JsonView(ShowFacultyEmails.class)
	@OneToMany(mappedBy="faculty")
	private Set<FacultyEmails> emails;
	
	@JsonView(ShowClassroom.class)
	@OneToMany(mappedBy="faculty")
	private Set<Classroom> classrooms;

	public Faculty() {}

	public Faculty(String name, University university, Address address, Teacher dean, Set<StudyProgram> studyPrograms,
			String description, Set<FacultyPhones> phones, Set<FacultyEmails> emails, Set<Classroom> classrooms) {
		super();
		this.name = name;
		this.university = university;
		this.address = address;
		this.dean = dean;
		this.studyPrograms = studyPrograms;
		this.description = description;
		this.phones = phones;
		this.emails = emails;
		this.classrooms = classrooms;
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
	
	public University getUniversity(){
		return university;
	}

	public void setUniversity(University university){
		this.university = university;
	}
	
	public Address getAddress(){
		return address;
	}

	public void setAddress(Address address){
		this.address = address;
	}
	
	public Teacher getDean(){
		return dean;
	}

	public void setDean(Teacher dean){
		this.dean = dean;
	}
	
	public Set<StudyProgram> getStudyPrograms(){
		return studyPrograms;
	}

	public void setStudyPrograms(Set<StudyProgram> studyPrograms){
		this.studyPrograms = studyPrograms;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<FacultyPhones> getPhones() {
		return phones;
	}

	public void setPhones(Set<FacultyPhones> phones) {
		this.phones = phones;
	}

	public Set<FacultyEmails> getEmails() {
		return emails;
	}

	public void setEmails(Set<FacultyEmails> emails) {
		this.emails = emails;
	}

	public Set<Classroom> getClassrooms() {
		return classrooms;
	}

	public void setClassrooms(Set<Classroom> classrooms) {
		this.classrooms = classrooms;
	}
	
}