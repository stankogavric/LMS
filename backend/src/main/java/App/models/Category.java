package App.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import java.util.Set;
import App.utils.View.ShowCategoryRole;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonView;
import App.utils.View.ShowForumTopic;


@Entity
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(length=128, nullable = false)
	private String title;

	@Column(nullable = false)
	private Boolean deleted;

	@JsonView(ShowCategoryRole.class)
	@OneToMany(mappedBy="category")
	private Set<CategoryRole> categoryRoles;

	@JsonView(ShowForumTopic.class)
	@OneToMany(mappedBy="category")
	private Set<ForumTopic> topics;

	@ManyToOne(cascade=CascadeType.ALL)
	private Forum forum;

	public Category() {}

	public Category(String title, Boolean deleted, Set<CategoryRole> categoryRoles, Set<ForumTopic> topics, Forum forum){
		this.title = title;
		this.deleted = deleted;
		this.categoryRoles = categoryRoles;
		this.topics = topics;
		this.forum = forum;
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
	
	public Boolean getDeleted(){
		return deleted;
	}

	public void setDeleted(Boolean deleted){
		this.deleted = deleted;
	}
	
	public Set<CategoryRole> getCategoryRoles(){
		return categoryRoles;
	}

	public void setCategoryRoles(Set<CategoryRole> categoryRoles){
		this.categoryRoles = categoryRoles;
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