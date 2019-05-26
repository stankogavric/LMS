package App.dto;

public class StudentDTO {


		private Long id;
		private String email;
		private String firstName;
		private String lastName;
		private String personalNumber;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
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

		public String getPersonalNumber() {
			return personalNumber;
		}

		public void setPersonalNumber(String personalNumber) {
			this.personalNumber = personalNumber;
		}

		public StudentDTO() {
			super();
		}
		

		


}
