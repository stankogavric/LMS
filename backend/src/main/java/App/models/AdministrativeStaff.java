package App.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;


@Entity
public class AdministrativeStaff {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade=CascadeType.ALL)
	private Address address;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private AccountData AccountData;

	@ManyToOne(cascade=CascadeType.ALL)
	private PersonalData personalData;

	public AdministrativeStaff() {}

	public AdministrativeStaff(Address address, AccountData AccountData, PersonalData personalData){
		this.address = address;
		this.AccountData = AccountData;
		this.personalData = personalData;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public Address getAddress(){
		return address;
	}

	public void setAddress(Address address){
		this.address = address;
	}
	
	public AccountData getAccountData(){
		return AccountData;
	}

	public void setAccountData(AccountData AccountData){
		this.AccountData = AccountData;
	}
	
	public PersonalData getPersonalData(){
		return personalData;
	}

	public void setPersonalData(PersonalData personalData){
		this.personalData = personalData;
	}
	
}