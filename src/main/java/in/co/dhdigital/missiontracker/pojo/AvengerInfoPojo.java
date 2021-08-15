package in.co.dhdigital.missiontracker.pojo;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonView;
import in.co.dhdigital.missiontracker.entity.MemberPrivilege;
import in.co.dhdigital.missiontracker.entity.Mission;
import in.co.dhdigital.missiontracker.utils.Enums.AvengerStatus;
import in.co.dhdigital.missiontracker.utils.Enums.Notification;
import in.co.dhdigital.missiontracker.view.JsonViews.MissionJsonView;
import in.co.dhdigital.missiontracker.view.JsonViews.UserJsonView;

public class AvengerInfoPojo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//	@JsonView(UserJsonView.class)
//	Long getId();
	@JsonView(UserJsonView.class)
	String username;
	
	@JsonView(UserJsonView.class)
	AvengerStatus status;
	
	@JsonView(UserJsonView.class)
	List<MemberPrivilege> privileges;
	
//	@Value("#{target.notification}")
	@JsonView(UserJsonView.class)
	Notification notifiction;
	
	@JsonView(UserJsonView.class)
	String contact;
	
	@JsonView(MissionJsonView.class)
	List<Mission> missions;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public AvengerStatus getStatus() {
		return status;
	}

	public void setStatus(AvengerStatus status) {
		this.status = status;
	}

	public List<MemberPrivilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<MemberPrivilege> privileges) {
		this.privileges = privileges;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
