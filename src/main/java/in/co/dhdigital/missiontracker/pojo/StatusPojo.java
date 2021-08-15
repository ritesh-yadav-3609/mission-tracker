package in.co.dhdigital.missiontracker.pojo;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;

import in.co.dhdigital.missiontracker.utils.Enums.Status;
import in.co.dhdigital.missiontracker.view.JsonViews.MissionJsonView;

public class StatusPojo {
	
	@NotNull
	@JsonView(MissionJsonView.class)
	private Long id;
	
	@NotNull
	@JsonView(MissionJsonView.class)
	private Status status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
}
