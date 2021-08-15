package in.co.dhdigital.missiontracker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.co.dhdigital.missiontracker.entity.Member;
import in.co.dhdigital.missiontracker.repository.MemberRepository;

@Service
public class JpaMemberDetailService implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Member member = memberRepository.findByUsername(username).orElseThrow(()->new BadCredentialsException("invalid username or password"));;
		return new SecureMember(member);
	}

}
