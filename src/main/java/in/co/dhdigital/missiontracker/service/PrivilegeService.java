package in.co.dhdigital.missiontracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.co.dhdigital.missiontracker.entity.Member;
import in.co.dhdigital.missiontracker.entity.MemberPrivilege;
import in.co.dhdigital.missiontracker.exception.NoSuchElementFoundException;
import in.co.dhdigital.missiontracker.pojo.PrivilegePojo;
import in.co.dhdigital.missiontracker.repository.MemberRepository;
import in.co.dhdigital.missiontracker.repository.PrivilegeRepository;

@Service
public class PrivilegeService {

	@Autowired
	private PrivilegeRepository privilegeRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	/*
	 * Add privilege for memeber
	 */
	public MemberPrivilege savePrivilege(PrivilegePojo privilegePojo) {
		Member member = memberRepository.findByUsername(privilegePojo.getMemberName()).orElseThrow(()-> new NoSuchElementFoundException("We dont have any member with name "+privilegePojo.getMemberName()));
		MemberPrivilege privilege = privilegeRepository.findByMemberAndPrivilege(member, privilegePojo.getPrivilege());
		if(privilege!=null) {
			return privilege;
		}
		MemberPrivilege memberPrivilege = new MemberPrivilege();
		memberPrivilege.setPrivilege(privilegePojo.getPrivilege());
		
		memberPrivilege.setMember(member);
		return privilegeRepository.save(memberPrivilege);
	}
	
	/*
	 * List all privilege of member
	 */
	public List<MemberPrivilege> getAllPrivilegeByMember(String memberName){
		return memberRepository.findByUsername(memberName).orElseThrow(()-> new NoSuchElementFoundException("We dont have any member with name "+memberName)).getPrivileges();
	}


	/*
	 * Delete privilege
	 */
	public void deletePrivilege(Long id) {
		privilegeRepository.deleteById(id);
	}

}
