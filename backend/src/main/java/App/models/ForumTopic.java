package App.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonView;
import App.utils.View.ShowPost;


@Entity
public class ForumTopic {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(length=128, nullable = false)
	private String title;

	@ManyToOne(cascade=CascadeType.ALL)
	private Category category;

	@JsonView(ShowPost.class)
	@OneToMany(mappedBy="topic")
	private Set<Post> posts;

	@ManyToOne(cascade=CascadeType.ALL)
	private ForumUser author;

	@Column(nullable = false)
	private Boolean deleted;

	@Column(nullable = false)
	private Boolean isLocked;

	public ForumTopic() {}

	public ForumTopic(String title, Category category, Set<Post> posts, ForumUser author, Boolean deleted, Boolean isLocked){
		this.title = title;
		this.category = category;
		this.posts = posts;
		this.author = author;
		this.deleted = deleted;
		this.isLocked = isLocked;
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
	
	public Category getCategory(){
		return category;
	}

	public void setCategory(Category category){
		this.category = category;
	}
	
	public Set<Post> getPosts(){
		return posts;
	}

	public void setPosts(Set<Post> posts){
		this.posts = posts;
	}
	
	public ForumUser getAuthor(){
		return author;
	}

	public void setAuthor(ForumUser author){
		this.author = author;
	}
	
	public Boolean getDeleted(){
		return deleted;
	}

	public void setDeleted(Boolean deleted){
		this.deleted = deleted;
	}
	
	public Boolean getIsLocked(){
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked){
		this.isLocked = isLocked;
	}
	
}