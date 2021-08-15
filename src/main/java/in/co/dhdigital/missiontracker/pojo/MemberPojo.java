package in.co.dhdigital.missiontracker.pojo;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import in.co.dhdigital.missiontracker.utils.Enums.Privilege;

public class MemberPojo {
	
	@NotEmpty
	private String username;
	
	@NotEmpty
	private String password;
	private List<Privilege> privileges;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Privilege> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}
	
	
}
