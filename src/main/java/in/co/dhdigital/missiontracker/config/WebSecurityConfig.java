package in.co.dhdigital.missiontracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import in.co.dhdigital.missiontracker.exception.AccessDeniedException;
import in.co.dhdigital.missiontracker.exception.JwtAuthenticationEntryPoint;
import in.co.dhdigital.missiontracker.utils.Enums.Privilege;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AccessDeniedException accessDeniedException;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	/*
	 * Http request configuration
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// Disable the csrf token
		http.csrf().disable();
//		http.cors();
		
		// Allow basic config authentication
		http.httpBasic();
		http.authorizeRequests()
		
		// End point without credentials
		.mvcMatchers("/fury").permitAll()
		.mvcMatchers("/login").permitAll()
		
		// Authorization configuration based on privileges of user
		.mvcMatchers(HttpMethod.POST,"/member").hasAnyAuthority(Privilege.ADDMEMBER.name())
		.mvcMatchers(HttpMethod.POST,"/avenger").hasAnyAuthority(Privilege.ADDAVENGER.name())
		.mvcMatchers(HttpMethod.GET,"/avenger").hasAnyAuthority(Privilege.VIEWAVENGER.name())
		.mvcMatchers(HttpMethod.PUT,"/avenger/**").hasAnyAuthority(Privilege.UPDATEAVENGER.name())
		.mvcMatchers(HttpMethod.GET,"/avenger/status").hasAnyAuthority(Privilege.VIEWAVENGER.name(),Privilege.VIEWAVENGERSTATUS.name())
		.mvcMatchers(HttpMethod.GET,"/avenger/notification").hasAnyAuthority(Privilege.VIEWAVENGER.name(),Privilege.VIEWNOTIFICATION.name())
		.mvcMatchers(HttpMethod.POST,"/avenger/notification").hasAnyAuthority(Privilege.UPDATENOTIFICATION.name())
		.mvcMatchers(HttpMethod.POST,"/mission").hasAnyAuthority(Privilege.ADDMISSION.name())
		.mvcMatchers(HttpMethod.GET,"/mission").hasAnyAuthority(Privilege.VIEWMISSION.name())
		.mvcMatchers(HttpMethod.PUT,"/mission").hasAnyAuthority(Privilege.UPDATEMISSION.name())
		.mvcMatchers(HttpMethod.DELETE,"/mission/**").hasAnyAuthority(Privilege.DELETEMISSION.name())
		.mvcMatchers(HttpMethod.PUT,"/mission/**").hasAnyAuthority(Privilege.UPDATESTATUS.name())
		.mvcMatchers("/member/privilege").hasAnyAuthority(Privilege.CONTOLPRIVILEGE.name())
		.and()
		
		// Exception handling at entry point and access deined exception
		.exceptionHandling().accessDeniedHandler(accessDeniedException).and()
		.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
		
		// Set state of session of server
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		// Added JWT filter for token authentication
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
	}

	/*
	 * Create a bean for BCryptPasswordEncoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	/*
	 * Bean for AuthenticationManager
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
