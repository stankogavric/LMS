package App.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.Column;


@Entity
public class CategoryRole {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(length=128, nullable = false)
	private String couldRead;

	@Column(length=128, nullable = false)
	private String couldWrite;

	@ManyToOne(cascade=CascadeType.ALL)
	private Category category;

	@ManyToOne(cascade=CascadeType.ALL)
	private ForumRole forumRole;

	public CategoryRole() {}

	public CategoryRole(String couldRead, String couldWrite, Category category, ForumRole forumRole){
		this.couldRead = couldRead;
		this.couldWrite = couldWrite;
		this.category = category;
		this.forumRole = forumRole;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public String getCouldRead(){
		return couldRead;
	}

	public void setCouldRead(String couldRead){
		this.couldRead = couldRead;
	}
	
	public String getCouldWrite(){
		return couldWrite;
	}

	public void setCouldWrite(String couldWrite){
		this.couldWrite = couldWrite;
	}
	
	public Category getCategory(){
		return category;
	}

	public void setCategory(Category category){
		this.category = category;
	}
	
	public ForumRole getForumRole(){
		return forumRole;
	}

	public void setForumRole(ForumRole forumRole){
		this.forumRole = forumRole;
	}
	
}