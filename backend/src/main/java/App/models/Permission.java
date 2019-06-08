package App.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

import App.utils.View.ShowAccountDataPermission;

@Entity
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String title;
	
	@JsonView(ShowAccountDataPermission.class)
	@OneToMany(mappedBy = "permission")
	private Set<AccountDataPermission> accDataPermissions;

	public Permission() {};
	
	public Permission(Long id, String title, Set<AccountDataPermission> accDataPermissions) {
		super();
		this.id = id;
		this.title = title;
		this.accDataPermissions = accDataPermissions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<AccountDataPermission> getAccounts() {
		return accDataPermissions;
	}

	public void AccountDataPermission(Set<AccountDataPermission> accDataPermissions) {
		this.accDataPermissions = accDataPermissions;
	}
	
}
