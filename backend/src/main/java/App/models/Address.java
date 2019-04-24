package App.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;


@Entity
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(length=128, nullable = false)
	private String street;

	@Column(length=128, nullable = false)
	private String number;

	@ManyToOne(cascade=CascadeType.ALL)
	private City city;

	public Address() {}

	public Address(String street, String number, City city){
		this.street = street;
		this.number = number;
		this.city = city;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public String getStreet(){
		return street;
	}

	public void setStreet(String street){
		this.street = street;
	}
	
	public String getNumber(){
		return number;
	}

	public void setNumber(String number){
		this.number = number;
	}
	
	public City getCity(){
		return city;
	}

	public void setCity(City city){
		this.city = city;
	}
	
}