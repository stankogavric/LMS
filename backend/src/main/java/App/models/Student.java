package App.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonView;

import App.utils.View.ShowStudentYear;
import App.utils.View.ShowSubjectAttendance;


@Entity
@Where(clause = "deleted = 'false'")
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@JsonView(ShowSubjectAttendance.class)
	@OneToMany(mappedBy="student")
	private Set<SubjectAttendance> subjectAttendances;

	@ManyToOne(cascade=CascadeType.ALL)
	private Address address;

	@JsonView(ShowStudentYear.class)
	@OneToMany(mappedBy="student", cascade=CascadeType.ALL)
	private Set<StudentYear> studentYears;

	@ManyToOne(cascade=CascadeType.ALL)
	private AccountData accountData;

	@ManyToOne(cascade=CascadeType.ALL)
	private PersonalData personalData;
	
	@NotNull
	private Boolean deleted = false;
	
	private int yearOfStudy;

	public Student() {}

	public Student(Set<SubjectAttendance> subjectAttendances, Address address, Set<StudentYear> studentYears,
			AccountData accountData, PersonalData personalData, @NotNull Boolean deleted, int yearOfStudy) {
		super();
		this.subjectAttendances = subjectAttendances;
		this.address = address;
		this.studentYears = studentYears;
		this.accountData = accountData;
		this.personalData = personalData;
		this.deleted = deleted;
		this.yearOfStudy = yearOfStudy;
	}
	
	public Student(Long id, String firstName, String lastName, String email) {
		this.id = id;
		this.personalData.setFirstName(firstName);
		this.personalData.setLastName(lastName);
		this.accountData.setEmail(email);
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
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
	
	public AccountData getAccountData(){
		return accountData;
	}

	public void setAccountData(AccountData accountData){
		this.accountData = accountData;
	}
	
	public PersonalData getPersonalData(){
		return personalData;
	}

	public void setPersonalData(PersonalData personalData){
		this.personalData = personalData;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public int getYearOfStudy() {
		return yearOfStudy;
	}

	public void setYearOfStudy(int yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}
	
}