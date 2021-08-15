package in.co.dhdigital.missiontracker.utils;

import org.springframework.stereotype.Service;

import in.co.dhdigital.missiontracker.entity.Avenger;
import in.co.dhdigital.missiontracker.utils.Enums.Notification;

@Service
public class NotificationManager {
	
	public void sendNotification(Avenger avenger, String message) {
		INotification notification;
		if(avenger.getNotifiction()==Notification.EMAIL) {
			notification = new EmailNotification();
		} else if(avenger.getNotifiction()==Notification.SMS) {
			notification = new SmsNotification();
		}else {
			notification = new PagerNotification();
		}
		notification.sendNotification(avenger.getContact(), message);
	}

}
