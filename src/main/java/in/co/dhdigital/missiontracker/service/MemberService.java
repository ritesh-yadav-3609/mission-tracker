package in.co.dhdigital.missiontracker.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.co.dhdigital.missiontracker.config.JwtTokenUtil;
import in.co.dhdigital.missiontracker.entity.Fury;
import in.co.dhdigital.missiontracker.entity.Member;
import in.co.dhdigital.missiontracker.entity.MemberPrivilege;
import in.co.dhdigital.missiontracker.pojo.JwtResponse;
import in.co.dhdigital.missiontracker.pojo.MemberPojo;
import in.co.dhdigital.missiontracker.repository.MemberRepository;
import in.co.dhdigital.missiontracker.security.JpaMemberDetailService;
import in.co.dhdigital.missiontracker.utils.Enums.Privilege;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JpaMemberDetailService JpaMemberDetailService;

	public Member saveMember(MemberPojo memberPojo) {
		Member member = new Member();
		member.setPrivileges(null);
		member.setPassword(passwordEncoder.encode(memberPojo.getPassword()));
		Optional.ofNullable(memberPojo.getPrivileges()).orElse(new ArrayList<>()).forEach(p->{
			MemberPrivilege memberPrivilege = new MemberPrivilege();
			memberPrivilege.setPrivilege(p);
			member.setPrivilege(memberPrivilege);
		});
		return memberRepository.save(member);
	}
	
	/*
	 * Save fury
	 */
	public void createFury() {
		try {
			Fury fury = new Fury();
			fury.setPassword(passwordEncoder.encode("fury"));
			fury.setUsername("fury");
			for (Privilege iterable : Privilege.values()) {
				MemberPrivilege memberPrivilege = new MemberPrivilege();
				memberPrivilege.setPrivilege(iterable);
				fury.setPrivilege(memberPrivilege);
			}
			memberRepository.save(fury);
		}catch (Exception e) {
			
		}
	}
	
	public JwtResponse createAuthenticationToken(Member member) throws Exception {
		authenticate(member.getUsername(), member.getPassword());
		final UserDetails userDetails = JpaMemberDetailService.loadUserByUsername(member.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return new JwtResponse(token);
	}
	
	/*
	 * Helper class to get login
	 */
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	} 
	
	
}
