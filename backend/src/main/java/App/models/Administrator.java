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
public class Administrator {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade=CascadeType.ALL)
	private AccountData accountData;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private PersonalData personalData;
	
	@NotNull
	private Boolean deleted = false;

	public Administrator() {}

	public Administrator(AccountData accountData, PersonalData personalData, Boolean deleted) {
		super();
		this.accountData = accountData;
		this.personalData = personalData;
		this.deleted = deleted;
	}

	public PersonalData getPersonalData() {
		return personalData;
	}

	public void setPersonalData(PersonalData personalData) {
		this.personalData = personalData;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public AccountData getAccountData(){
		return accountData;
	}

	public void setAccountData(AccountData accountData){
		this.accountData = accountData;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
}