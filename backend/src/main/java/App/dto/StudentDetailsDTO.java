package App.dto;

import java.util.Collection;
import java.util.Set;

public class StudentDetailsDTO {
	
	private Long id;
	private String firstName, lastName, email, profilePicturePath;
	
	private Collection<StudentYearDTO> studentYears;
	
	public StudentDetailsDTO() {}	

	public StudentDetailsDTO(Long id, String firstName, String lastName, String email, String profilePicturePath,
			Set<StudentYearDTO> studentYears) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.profilePicturePath = profilePicturePath;
		this.studentYears = studentYears;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfilePicturePath() {
		return profilePicturePath;
	}

	public void setProfilePicturePath(String profilePicturePath) {
		this.profilePicturePath = profilePicturePath;
	}

	public Collection<StudentYearDTO> getStudentYears() {
		return studentYears;
	}

	public void setStudentYears(Collection<StudentYearDTO> studentYears) {
		this.studentYears = studentYears;
	}


}
