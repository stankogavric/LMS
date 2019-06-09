package App.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import java.util.Set;
import App.utils.View.ShowForumUserForumRole;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonView;
import App.utils.View.ShowForumTopic;
import App.utils.View.ShowPost;


@Entity
public class ForumUser {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@JsonView(ShowForumUserForumRole.class)
	@OneToMany(mappedBy="user")
	private Set<ForumUserForumRole> roles;

	@ManyToOne(cascade=CascadeType.ALL)
	private AccountData account;

	@JsonView(ShowPost.class)
	@OneToMany(mappedBy="author")
	private Set<Post> posts;

	@JsonView(ShowForumTopic.class)
	@OneToMany(mappedBy="author")
	private Set<ForumTopic> topics;

	@ManyToOne(cascade=CascadeType.ALL)
	private Forum forum;

	public ForumUser() {}

	public ForumUser(Set<ForumUserForumRole> roles, AccountData account, Set<Post> posts, Set<ForumTopic> topics, Forum forum){
		this.roles = roles;
		this.account = account;
		this.posts = posts;
		this.topics = topics;
		this.forum = forum;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public Set<ForumUserForumRole> getRoles(){
		return roles;
	}

	public void setRoles(Set<ForumUserForumRole> roles){
		this.roles = roles;
	}
	
	public AccountData getAccount(){
		return account;
	}

	public void setAccount(AccountData account){
		this.account = account;
	}
	
	public Set<Post> getPosts(){
		return posts;
	}

	public void setPosts(Set<Post> posts){
		this.posts = posts;
	}
	
	public Set<ForumTopic> getTopics(){
		return topics;
	}

	public void setTopics(Set<ForumTopic> topics){
		this.topics = topics;
	}
	
	public Forum getForum(){
		return forum;
	}

	public void setForum(Forum forum){
		this.forum = forum;
	}
	
}