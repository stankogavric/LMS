package App.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import java.util.Set;
import App.utils.View.ShowForumUserForumRole;
import App.utils.View.ShowCategoryRole;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
public class ForumRole {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(length=128, nullable = false)
	private String title;

	@JsonView(ShowForumUserForumRole.class)
	@OneToMany(mappedBy="role")
	private Set<ForumUserForumRole> users;

	@JsonView(ShowCategoryRole.class)
	@OneToMany(mappedBy="forumRole")
	private Set<CategoryRole> categoryRoles;

	public ForumRole() {}

	public ForumRole(String title, Set<ForumUserForumRole> users, Set<CategoryRole> categoryRoles){
		this.title = title;
		this.users = users;
		this.categoryRoles = categoryRoles;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	}
	
	public Set<ForumUserForumRole> getUsers(){
		return users;
	}

	public void setUsers(Set<ForumUserForumRole> users){
		this.users = users;
	}
	
	public Set<CategoryRole> getCategoryRoles(){
		return categoryRoles;
	}

	public void setCategoryRoles(Set<CategoryRole> categoryRoles){
		this.categoryRoles = categoryRoles;
	}
	
}