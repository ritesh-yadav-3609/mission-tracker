package in.co.dhdigital.missiontracker.utils;

public class SmsNotification implements INotification{

	@Override
	public void sendNotification(String contact, String message) {
		System.out.println("\n\nSMS send to phone number "+contact+" with message "+message+"\n\n");
	}

}
