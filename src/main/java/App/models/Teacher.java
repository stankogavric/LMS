package App.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import App.utils.View.ShowTitle;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;


@Entity
public class Teacher {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(length=128, nullable = false)
	private String name;

	@Column(length=128, nullable = false)
	private String biography;

	@Column(length=128, nullable = false)
	private String jmbg;

	@JsonView(ShowTitle.class)
	@OneToMany(mappedBy="None")
	private Set<Title> titles;

	@ManyToOne(cascade=CascadeType.ALL)
	private Address address;

	public Teacher() {}

	public Teacher(String name, String biography, String jmbg, Set<Title> titles, Address address){
		this.name = name;
		this.biography = biography;
		this.jmbg = jmbg;
		this.titles = titles;
		this.address = address;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public String getBiography(){
		return biography;
	}

	public void setBiography(String biography){
		this.biography = biography;
	}
	
	public String getJmbg(){
		return jmbg;
	}

	public void setJmbg(String jmbg){
		this.jmbg = jmbg;
	}
	
	public Set<Title> getTitles(){
		return titles;
	}

	public void setTitles(Set<Title> titles){
		this.titles = titles;
	}
	
	public Address getAddress(){
		return address;
	}

	public void setAddress(Address address){
		this.address = address;
	}
	
}