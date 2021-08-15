package in.co.dhdigital.missiontracker.pojo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAlias;

import in.co.dhdigital.missiontracker.utils.Enums.Privilege;

public class PrivilegePojo {
	
	@NotNull
	private Privilege privilege;
	
	@JsonAlias("member")
	@NotEmpty
	private String memberName;

	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	
}
