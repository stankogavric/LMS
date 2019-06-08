package App.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.Set;
import App.utils.View.ShowPostFile;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Date timeOfPublication;

	@Column(length=128, nullable = false)
	private String content;

	@Column(nullable = false)
	private Boolean deleted;

	@JsonView(ShowPostFile.class)
	@OneToMany(mappedBy="post")
	private Set<PostFile> attachment;

	@ManyToOne(cascade=CascadeType.ALL)
	private ForumUser author;

	@ManyToOne(cascade=CascadeType.ALL)
	private ForumTopic topic;

	public Post() {}

	public Post(Date timeOfPublication, String content, Boolean deleted, Set<PostFile> attachment, ForumUser author, ForumTopic topic){
		this.timeOfPublication = timeOfPublication;
		this.content = content;
		this.deleted = deleted;
		this.attachment = attachment;
		this.author = author;
		this.topic = topic;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public Date getTimeOfPublication(){
		return timeOfPublication;
	}

	public void setTimeOfPublication(Date timeOfPublication){
		this.timeOfPublication = timeOfPublication;
	}
	
	public String getContent(){
		return content;
	}

	public void setContent(String content){
		this.content = content;
	}
	
	public Boolean getDeleted(){
		return deleted;
	}

	public void setDeleted(Boolean deleted){
		this.deleted = deleted;
	}
	
	public Set<PostFile> getAttachment(){
		return attachment;
	}

	public void setAttachment(Set<PostFile> attachment){
		this.attachment = attachment;
	}
	
	public ForumUser getAuthor(){
		return author;
	}

	public void setAuthor(ForumUser author){
		this.author = author;
	}
	
	public ForumTopic getTopic(){
		return topic;
	}

	public void setTopic(ForumTopic topic){
		this.topic = topic;
	}
	
}