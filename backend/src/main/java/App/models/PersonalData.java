package App.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;


@Entity
public class PersonalData {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(length=128, nullable = false)
	private String firstName;
	
	@Column(length=128, nullable = false)
	private String lastName;

	@Column(length=128, nullable = false)
	private String personalNumber;

	@Column(length=128)
	private String profilePicturePath;

	public PersonalData() {}

	public PersonalData(String firstName, String lastName, String personalNumber, String profilePicturePath) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.personalNumber = personalNumber;
		this.profilePicturePath = profilePicturePath;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPersonalNumber(){
		return personalNumber;
	}

	public void setPersonalNumber(String personalNumber){
		this.personalNumber = personalNumber;
	}
	
	public String getProfilePicturePath(){
		return profilePicturePath;
	}

	public void setProfilePicturePath(String profilePicturePath){
		this.profilePicturePath = profilePicturePath;
	}
	
}