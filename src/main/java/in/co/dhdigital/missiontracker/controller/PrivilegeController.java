package in.co.dhdigital.missiontracker.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import in.co.dhdigital.missiontracker.entity.MemberPrivilege;
import in.co.dhdigital.missiontracker.pojo.PrivilegePojo;
import in.co.dhdigital.missiontracker.service.PrivilegeService;
import in.co.dhdigital.missiontracker.view.JsonViews.PrivilegeJsonView;

@RestController
@RequestMapping("member/privilege")
public class PrivilegeController {

	@Autowired
	private PrivilegeService privilegeService;
	
	/*
	 * Get privilege by member name
	 */
	@GetMapping("")
	@JsonView(PrivilegeJsonView.class)
	public List<MemberPrivilege> getAllPrivilegeByMember(@RequestParam("member") String memberName){
		return privilegeService.getAllPrivilegeByMember(memberName);
	}
	
	/*
	 * Assign new privilege to the member
	 */
	@PostMapping("")
	@JsonView(PrivilegeJsonView.class)
	public MemberPrivilege savePrivilege(@Valid @RequestBody PrivilegePojo privilegePojo) {
		return privilegeService.savePrivilege(privilegePojo);
	}
	
	/*
	 * Delete already exiting privilege for a member
	 */
	@DeleteMapping("/{id}")
	@JsonView(PrivilegeJsonView.class)
	public void deletePrivilege(@PathVariable("id") Long id) {
		privilegeService.deletePrivilege(id);
	}
	
}
