package App.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;


@Entity
@Where(clause = "deleted = 'false'")
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
	
	@NotNull
	private Boolean deleted = false;

	public AdministrativeStaff() {}

	public AdministrativeStaff(Address address, App.models.AccountData accountData, PersonalData personalData, Boolean deleted) {
		super();
		this.address = address;
		AccountData = accountData;
		this.personalData = personalData;
		this.deleted = deleted;
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

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
}