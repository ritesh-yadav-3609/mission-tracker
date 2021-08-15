package in.co.dhdigital.missiontracker.pojo;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonView;

import in.co.dhdigital.missiontracker.utils.Enums.Notification;
import in.co.dhdigital.missiontracker.view.JsonViews.UserJsonView;

public class NotificationPojo {

	@NotEmpty
	@JsonView(UserJsonView.class)
	private String contact;	
	
	@NotEmpty
	@JsonView(UserJsonView.class)
	private Notification type;
	
	@JsonAlias("avenger")
	@JsonView(UserJsonView.class)
	private String avenger;

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Notification getType() {
		return type;
	}

	public void setType(Notification type) {
		this.type = type;
	}

	public String getAvenger() {
		return avenger;
	}

	public void setAvenger(String avenger) {
		this.avenger = avenger;
	}
	
	
}

