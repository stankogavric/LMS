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
	private String name;

	@Column(length=128, nullable = false)
	private String personalNumber;

	@Column(length=128, nullable = false)
	private String profilePicturePath;

	public PersonalData() {}

	public PersonalData(String name, String personalNumber, String profilePicturePath){
		this.name = name;
		this.personalNumber = personalNumber;
		this.profilePicturePath = profilePicturePath;
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