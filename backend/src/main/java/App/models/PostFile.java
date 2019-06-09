package App.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.Column;


@Entity
public class PostFile {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(length=128, nullable = false)
	private String description;

	@Column(length=128, nullable = false)
	private String url;

	@ManyToOne(cascade=CascadeType.ALL)
	private Post post;

	public PostFile() {}

	public PostFile(String description, String url, Post post){
		this.description = description;
		this.url = url;
		this.post = post;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}
	
	public String getUrl(){
		return url;
	}

	public void setUrl(String url){
		this.url = url;
	}
	
	public Post getPost(){
		return post;
	}

	public void setPost(Post post){
		this.post = post;
	}
	
}