package App.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import App.utils.View.ShowTitle;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;


@Entity
public class Teacher {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(length=128, nullable = false)
	private String biography;

	@JsonView(ShowTitle.class)
	@OneToMany(mappedBy="teacher")
	private Set<Title> titles;

	@ManyToOne(cascade=CascadeType.ALL)
	private Address address;

	@ManyToOne(cascade=CascadeType.ALL)
	private AccountData accountData;

	@ManyToOne(cascade=CascadeType.ALL)
	private PersonalData personalData;

	public Teacher() {}

	public Teacher(String biography, Set<Title> titles, Address address, AccountData accountData, PersonalData personalData){
		this.biography = biography;
		this.titles = titles;
		this.address = address;
		this.accountData = accountData;
		this.personalData = personalData;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public String getBiography(){
		return biography;
	}

	public void setBiography(String biography){
		this.biography = biography;
	}
	
	public Set<Title> getTitles(){
		return titles;
	}

	public void setTitles(Set<Title> titles){
		this.titles = titles;
	}
	
	public Address getAddress(){
		return address;
	}

	public void setAddress(Address address){
		this.address = address;
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