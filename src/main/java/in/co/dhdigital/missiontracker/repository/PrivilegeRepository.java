package in.co.dhdigital.missiontracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.co.dhdigital.missiontracker.entity.Member;
import in.co.dhdigital.missiontracker.entity.MemberPrivilege;
import in.co.dhdigital.missiontracker.utils.Enums.Privilege;

public interface PrivilegeRepository extends JpaRepository<MemberPrivilege, Long> {

	List<MemberPrivilege> findAllByMember(Long id);

	MemberPrivilege findByMemberAndPrivilege(Member member, Privilege privilege);
	

}
