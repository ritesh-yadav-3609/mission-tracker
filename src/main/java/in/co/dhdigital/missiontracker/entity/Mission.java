package in.co.dhdigital.missiontracker.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonView;
import in.co.dhdigital.missiontracker.utils.Enums.Status;
import in.co.dhdigital.missiontracker.view.JsonViews.CommonJsonViews;
import in.co.dhdigital.missiontracker.view.JsonViews.MissionJsonView;

@Entity
@Table(indexes = { @Index(name = "member_status_index", columnList = "avenger, status"),})
public class Mission extends AbstractEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(MissionJsonView.class)
	private Long id;
	
	@JsonView(CommonJsonViews.class)
	@NotEmpty
	private String name;
	
	@JsonView(CommonJsonViews.class)
	private String description;
	
	@JsonView(CommonJsonViews.class)
	@NotNull
	private Status status;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "avenger", referencedColumnName = "id", nullable = false)
	@JsonView(MissionJsonView.class)
	private Avenger avenger;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Avenger getAvenger() {
		return avenger;
	}

	public void setAvenger(Avenger avenger) {
		this.avenger = avenger;
	}
	

}
