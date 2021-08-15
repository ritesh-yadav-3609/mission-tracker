package in.co.dhdigital.missiontracker.pojo;

import javax.validation.constraints.NotEmpty;

import in.co.dhdigital.missiontracker.utils.Enums.AvengerStatus;

public class AvengerStatusPojo {

	@NotEmpty
	private String avenger;
	
	@NotEmpty
	private AvengerStatus status;

	public String getAvenger() {
		return avenger;
	}

	public void setAvenger(String avenger) {
		this.avenger = avenger;
	}

	public AvengerStatus getStatus() {
		return status;
	}

	public void setStatus(AvengerStatus status) {
		this.status = status;
	}
	
	
}
