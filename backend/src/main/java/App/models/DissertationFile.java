package App.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class DissertationFile {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=128, nullable = false)
	private String url;
	
	@OneToOne(mappedBy = "file")
	private Dissertation dissertation;

	public DissertationFile(String url, Dissertation dissertation) {
		super();
		this.url = url;
		this.dissertation = dissertation;
	}
	
	public DissertationFile() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Dissertation getDissertation() {
		return dissertation;
	}

	public void setDissertation(Dissertation dissertation) {
		this.dissertation = dissertation;
	}
	
	
}
