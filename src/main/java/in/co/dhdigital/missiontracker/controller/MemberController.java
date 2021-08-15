package in.co.dhdigital.missiontracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;
import in.co.dhdigital.missiontracker.entity.Member;
import in.co.dhdigital.missiontracker.pojo.JwtResponse;
import in.co.dhdigital.missiontracker.service.MemberService;
import in.co.dhdigital.missiontracker.view.JsonViews.UserJsonView;

@RestController
@CrossOrigin
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	/*
	 * Add new member in team
	 */
//	@PostMapping("/member")
//	@JsonView(UserJsonView.class)
//	public Member saveMember(@Valid @RequestBody MemberPojo memberPojo) {
//		return memberService.saveMember(memberPojo);
//	}
	
	/*
	 * Save data of fury in database
	 */
	@GetMapping("/fury")
	@JsonView(UserJsonView.class)
	public void createFury() {
		memberService.createFury();
	}
	
	/*
	 * Login end point for login to get JWT token
	 */
	@PostMapping(value = "/login")
	public JwtResponse createAuthenticationToken(@RequestBody Member member) throws Exception {
		return memberService.createAuthenticationToken(member);
	}
}
