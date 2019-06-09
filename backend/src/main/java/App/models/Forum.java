package App.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;
import App.utils.View.ShowCategory;
import App.utils.View.ShowForumUser;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
public class Forum {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@JsonView(ShowForumUser.class)
	@OneToMany(mappedBy="forum")
	private Set<ForumUser> users;

	@JsonView(ShowCategory.class)
	@OneToMany(mappedBy="forum")
	private Set<Category> categories;

	public Forum() {}

	public Forum(Set<ForumUser> users, Set<Category> categories){
		this.users = users;
		this.categories = categories;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public Set<ForumUser> getUsers(){
		return users;
	}

	public void setUsers(Set<ForumUser> users){
		this.users = users;
	}
	
	public Set<Category> getCategories(){
		return categories;
	}

	public void setCategories(Set<Category> categories){
		this.categories = categories;
	}
	
}