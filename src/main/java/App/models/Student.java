package App.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import App.utils.View.ShowSubjectAttendance;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.Set;
import App.utils.View.ShowStudentYear;


@Entity
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(length=128, nullable = false)
	private String name;

	@Column(length=128, nullable = false)
	private String jmbg;

	@JsonView(ShowSubjectAttendance.class)
	@OneToMany(mappedBy="student")
	private Set<SubjectAttendance> subjectAttendances;

	@ManyToOne(cascade=CascadeType.ALL)
	private Address address;

	@JsonView(ShowStudentYear.class)
	@OneToMany(mappedBy="student")
	private Set<StudentYear> studentYears;

	@ManyToOne(cascade=CascadeType.ALL)
	private RegisteredUser registeredUser;

	public Student() {}

	public Student(String name, String jmbg, Set<SubjectAttendance> subjectAttendances, Address address, Set<StudentYear> studentYears, RegisteredUser registeredUser){
		this.name = name;
		this.jmbg = jmbg;
		this.subjectAttendances = subjectAttendances;
		this.address = address;
		this.studentYears = studentYears;
		this.registeredUser = registeredUser;
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
	
	public String getJmbg(){
		return jmbg;
	}

	public void setJmbg(String jmbg){
		this.jmbg = jmbg;
	}
	
	public Set<SubjectAttendance> getSubjectAttendances(){
		return subjectAttendances;
	}

	public void setSubjectAttendances(Set<SubjectAttendance> subjectAttendances){
		this.subjectAttendances = subjectAttendances;
	}
	
	public Address getAddress(){
		return address;
	}

	public void setAddress(Address address){
		this.address = address;
	}
	
	public Set<StudentYear> getStudentYears(){
		return studentYears;
	}

	public void setStudentYears(Set<StudentYear> studentYears){
		this.studentYears = studentYears;
	}
	
	public RegisteredUser getRegisteredUser(){
		return registeredUser;
	}

	public void setRegisteredUser(RegisteredUser registeredUser){
		this.registeredUser = registeredUser;
	}
	
}