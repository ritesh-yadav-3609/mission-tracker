package in.co.dhdigital.missiontracker.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;
import com.fasterxml.jackson.annotation.JsonView;
import in.co.dhdigital.missiontracker.utils.Enums.Privilege;
import in.co.dhdigital.missiontracker.view.JsonViews.CommonJsonViews;
import in.co.dhdigital.missiontracker.view.JsonViews.PrivilegeJsonView;

@Entity
@Table(indexes = { @Index(name = "member_index", columnList = "member_id"),}, 
		uniqueConstraints = { @UniqueConstraint(columnNames = {"member_id", "privilege"})})
public class MemberPrivilege extends AbstractEntity implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(CommonJsonViews.class)
	private Long id;
	
	@JsonView(CommonJsonViews.class)
	@NotNull
	private Privilege privilege;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "member_id", referencedColumnName = "id", nullable = false)
	@JsonView(PrivilegeJsonView.class)
	private Member member;
	
	@Override
	public String getAuthority() {
		return this.getPrivilege().name();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
