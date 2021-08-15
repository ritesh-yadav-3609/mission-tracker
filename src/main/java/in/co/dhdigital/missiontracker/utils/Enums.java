package in.co.dhdigital.missiontracker.utils;

public class Enums{
	public enum Privilege {
		ADDMEMBER("1"), VIEWAVENGER("2"), ADDAVENGER("3"), UPDATEAVENGER("4"), VIEWAVENGERSTATUS("5"), 
		VIEWNOTIFICATION("6"), UPDATENOTIFICATION("7"), ADDMISSION("8"), VIEWMISSION("9"), UPDATEMISSION("10"), 
		DELETEMISSION("11"), UPDATESTATUS("12"), CONTOLPRIVILEGE("13");
		private String value; 
	    public String getValue() { 
	        return this.value; 
	    }
	    private Privilege(String value){ 
	        this.value = value; 
	    }
	}
	
	public enum Notification {
		SMS("1"), EMAIL("2"), PAGER("3");
		private String value; 
	    public String getValue() { 
	        return this.value; 
	    }
	    private Notification(String value){ 
	        this.value = value; 
	    }
	}
	
	public enum AvengerStatus {
		FREE(0), PARTIAL(1), BUSY(2);
		private Integer value; 
	    public Integer getValue() { 
	        return this.value; 
	    }
	    private AvengerStatus(Integer value){ 
	        this.value = value; 
	    }
	    
	    public static AvengerStatus fromString(Integer value) {
	        for (AvengerStatus b : AvengerStatus.values()) {
	            if (b.value==value) {
	                return b;
	            }
	        }
	        return null;
	    }
	}
	
	public enum Status {
		ASSIGN("2"), COMPLETED("1");
		private String value; 
	    public String getValue() { 
	        return this.value; 
	    }
	    private Status(String value){ 
	        this.value = value; 
	    }
	}

}
