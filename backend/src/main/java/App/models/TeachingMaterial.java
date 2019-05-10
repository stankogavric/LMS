package App.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

import App.utils.View.ShowAuthor;
import App.utils.View.ShowFile;

@Entity
public class TeachingMaterial {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=128, nullable = false)
	private String name;
	
	@JsonView(ShowAuthor.class)
	@OneToMany(mappedBy="teachingMaterial")
	private Set<Author> authors;

	@Column(nullable = false)
	private Date publicationDate;
	
	@JsonView(ShowFile.class)
	@OneToMany(mappedBy="teachingMaterial")
	private Set<File> files;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private SubjectRealization subjectRealization;

	public TeachingMaterial() {
		super();
	}

	public TeachingMaterial(String name, Set<Author> authors, Date publicationDate, Set<File> files,
			SubjectRealization subjectRealization) {
		super();
		this.name = name;
		this.authors = authors;
		this.publicationDate = publicationDate;
		this.files = files;
		this.subjectRealization = subjectRealization;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public Set<File> getFiles() {
		return files;
	}

	public void setFiles(Set<File> files) {
		this.files = files;
	}

	public SubjectRealization getSubjectRealization() {
		return subjectRealization;
	}

	public void setSubjectRealization(SubjectRealization subjectRealization) {
		this.subjectRealization = subjectRealization;
	}

}
