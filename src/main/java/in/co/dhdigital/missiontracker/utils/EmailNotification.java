package in.co.dhdigital.missiontracker.utils;

public class EmailNotification implements INotification {

	@Override
	public void sendNotification(String contact, String message) {
		System.out.println("\n\nEmail send to "+contact+" with message "+message+"\n\n");
	}

}
