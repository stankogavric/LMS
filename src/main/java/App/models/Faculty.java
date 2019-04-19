package App.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.CascadeType;
import App.utils.View.ShowStudyProgram;
import javax.persistence.OneToMany;
import java.util.Set;


@Entity
public class Faculty {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(length=128, nullable = false)
	private String name;

	@ManyToOne(cascade=CascadeType.ALL)
	private University university;

	@ManyToOne(cascade=CascadeType.ALL)
	private Address address;

	@ManyToOne(cascade=CascadeType.ALL)
	private Teacher dean;

	@JsonView(ShowStudyProgram.class)
	@OneToMany(mappedBy="faculty")
	private Set<StudyProgram> studyPrograms;

	public Faculty() {}

	public Faculty(String name, University university, Address address, Teacher dean, Set<StudyProgram> studyPrograms){
		this.name = name;
		this.university = university;
		this.address = address;
		this.dean = dean;
		this.studyPrograms = studyPrograms;
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
	
	public University getUniversity(){
		return university;
	}

	public void setUniversity(University university){
		this.university = university;
	}
	
	public Address getAddress(){
		return address;
	}

	public void setAddress(Address address){
		this.address = address;
	}
	
	public Teacher getDean(){
		return dean;
	}

	public void setDean(Teacher dean){
		this.dean = dean;
	}
	
	public Set<StudyProgram> getStudyPrograms(){
		return studyPrograms;
	}

	public void setStudyPrograms(Set<StudyProgram> studyPrograms){
		this.studyPrograms = studyPrograms;
	}
	
}