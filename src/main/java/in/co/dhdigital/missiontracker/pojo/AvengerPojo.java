package in.co.dhdigital.missiontracker.pojo;

import javax.validation.constraints.NotNull;

public class AvengerPojo extends MemberPojo {
	
	@NotNull
	private NotificationPojo notification;

	public NotificationPojo getNotification() {
		return notification;
	}

	public void setNotification(NotificationPojo notification) {
		this.notification = notification;
	}
	
}
