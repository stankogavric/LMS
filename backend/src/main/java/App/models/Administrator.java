package App.models;

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
	private AccountData accountData;

	public Administrator() {}

	public Administrator(AccountData accountData){
		this.accountData = accountData;
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
	
}