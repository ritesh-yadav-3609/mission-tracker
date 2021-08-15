package in.co.dhdigital.missiontracker.utils;

public class PagerNotification implements INotification{

	@Override
	public void sendNotification(String contact, String message) {
		System.out.println("\n\nMessage send to pager id "+contact+" with message "+message+"\n\n");
	}

}
