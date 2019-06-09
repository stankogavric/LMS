package App.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

import App.utils.View.ShowAccountDataPermission;


@Entity
public class AccountData {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(length=128, nullable = false, unique = true)
	private String username;

	@Column(length=128, nullable = false)
	private String password;

	@Column(length=128, nullable = false)
	private String email;

	@JsonView(ShowAccountDataPermission.class)
	@OneToMany(mappedBy = "accountData", cascade = CascadeType.ALL)
	private Set<AccountDataPermission> accountDataPermissions;
	
	public AccountData() {}

	public AccountData(String username, String password, String email, Set<AccountDataPermission> accountDataPermissions) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.accountDataPermissions = accountDataPermissions;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public String getUsername(){
		return username;
	}

	public void setUsername(String username){
		this.username = username;
	}
	
	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}
	
	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public Set<AccountDataPermission> getAccountDataPermissions() {
		return accountDataPermissions;
	}

	public void setAccountDataPermissions(Set<AccountDataPermission> accountDataPermissions) {
		this.accountDataPermissions = accountDataPermissions;
	}

}