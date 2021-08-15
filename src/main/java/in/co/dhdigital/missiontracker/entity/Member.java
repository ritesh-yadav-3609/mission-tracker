package in.co.dhdigital.missiontracker.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonView;
import in.co.dhdigital.missiontracker.view.JsonViews.CommonJsonViews;
import in.co.dhdigital.missiontracker.view.JsonViews.UserJsonView;

@Entity(name="members")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("MEMBER")
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "TYPE")
public class Member extends AbstractEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(UserJsonView.class)
	private Long id;
	
	@Column(unique = true, nullable = false)
	@NotEmpty
	@JsonView(CommonJsonViews.class)
	private String username;
	
	@NotEmpty
	private String password;
	
	@OneToMany(mappedBy = "member",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonView(UserJsonView.class)
	private List<MemberPrivilege> privileges;
	
	public void setPrivilege(MemberPrivilege memberPrivilege) {
		if(privileges==null) {
			privileges = new ArrayList<>();
		}
		memberPrivilege.setMember(this);
		privileges.add(memberPrivilege);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<MemberPrivilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<MemberPrivilege> privileges) {
		this.privileges = privileges;
	}
	
	
}
