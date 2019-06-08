package App.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;


@Entity
public class ForumUserForumRole {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade=CascadeType.ALL)
	private ForumUser user;

	@ManyToOne(cascade=CascadeType.ALL)
	private ForumRole role;

	public ForumUserForumRole() {}

	public ForumUserForumRole(ForumUser user, ForumRole role){
		this.user = user;
		this.role = role;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public ForumUser getUser(){
		return user;
	}

	public void setUser(ForumUser user){
		this.user = user;
	}
	
	public ForumRole getRole(){
		return role;
	}

	public void setRole(ForumRole role){
		this.role = role;
	}
	
}