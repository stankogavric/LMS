package App.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;


@Entity
public class Administrator {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade=CascadeType.ALL)
	private RegisteredUser registeredUser;

	public Administrator() {}

	public Administrator(RegisteredUser registeredUser){
		this.registeredUser = registeredUser;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public RegisteredUser getRegisteredUser(){
		return registeredUser;
	}

	public void setRegisteredUser(RegisteredUser registeredUser){
		this.registeredUser = registeredUser;
	}
	
}