package App.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonView;
import App.utils.View.ShowCity;
import javax.persistence.OneToMany;


@Entity
public class Country {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(length=128, nullable = false)
	private String name;

	@JsonView(ShowCity.class)
	@OneToMany(mappedBy="country")
	private Set<City> cities;

	public Country() {}

	public Country(String name, Set<City> cities){
		this.name = name;
		this.cities = cities;
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
	
	public Set<City> getCities(){
		return cities;
	}

	public void setCities(Set<City> cities){
		this.cities = cities;
	}
	
}