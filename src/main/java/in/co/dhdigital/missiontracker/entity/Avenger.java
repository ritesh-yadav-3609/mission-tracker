package in.co.dhdigital.missiontracker.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonView;
import in.co.dhdigital.missiontracker.utils.Enums.Notification;
import in.co.dhdigital.missiontracker.view.JsonViews.UserJsonView;

@Entity
@DiscriminatorValue("AVENGER")
public class Avenger extends Member{

	@JsonView(UserJsonView.class)
	private Notification notifiction;
	
	@JsonView(UserJsonView.class)
	private String contact;
	
	@OneToMany(mappedBy = "avenger", cascade = CascadeType.ALL)
	private List<Mission> missions;
	
	public void setMission(Mission mission) {
		if(missions==null) {
			missions = new ArrayList<>();
		}
		missions.add(mission);
	}

	public Notification getNotifiction() {
		return notifiction;
	}

	public void setNotifiction(Notification notifiction) {
		this.notifiction = notifiction;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public List<Mission> getMissions() {
		return missions;
	}

	public void setMissions(List<Mission> missions) {
		this.missions = missions;
	}
	
	
	
}
