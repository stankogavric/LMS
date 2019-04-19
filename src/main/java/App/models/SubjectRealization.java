package App.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonView;
import App.utils.View.ShowTeacherRealization;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.Set;


@Entity
public class SubjectRealization {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@JsonView(ShowTeacherRealization.class)
	@OneToMany(mappedBy="subjectRealization")
	private Set<TeacherRealization> teacherRealizations;

	@ManyToOne(cascade=CascadeType.ALL)
	private Subject subject;

	public SubjectRealization() {}

	public SubjectRealization(Set<TeacherRealization> teacherRealizations, Subject subject){
		this.teacherRealizations = teacherRealizations;
		this.subject = subject;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public Set<TeacherRealization> getTeacherRealizations(){
		return teacherRealizations;
	}

	public void setTeacherRealizations(Set<TeacherRealization> teacherRealizations){
		this.teacherRealizations = teacherRealizations;
	}
	
	public Subject getSubject(){
		return subject;
	}

	public void setSubject(Subject subject){
		this.subject = subject;
	}
	
}