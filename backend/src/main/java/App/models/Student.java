package App.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import App.utils.View.ShowSubjectAttendance;
import App.utils.View.ShowStudentYear;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;


@Entity
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
	@OneToMany(mappedBy="student")
	private Set<StudentYear> studentYears;

	@ManyToOne(cascade=CascadeType.ALL)
	private AccountData accountData;

	@ManyToOne(cascade=CascadeType.ALL)
	private PersonalData personalData;

	public Student() {}

	public Student(Set<SubjectAttendance> subjectAttendances, Address address, Set<StudentYear> studentYears, AccountData accountData, PersonalData personalData){
		this.subjectAttendances = subjectAttendances;
		this.address = address;
		this.studentYears = studentYears;
		this.accountData = accountData;
		this.personalData = personalData;
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
	
}