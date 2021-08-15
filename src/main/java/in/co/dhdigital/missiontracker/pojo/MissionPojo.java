package in.co.dhdigital.missiontracker.pojo;

import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonAlias;

public class MissionPojo {
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String description;
	
	@JsonAlias("avenger")
	@NotEmpty
	private String avengerName;

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

	public String getAvengerName() {
		return avengerName;
	}

	public void setAvengerName(String avengerName) {
		this.avengerName = avengerName;
	}
	
	
	
}
