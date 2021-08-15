package in.co.dhdigital.missiontracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.co.dhdigital.missiontracker.entity.Avenger;
import in.co.dhdigital.missiontracker.entity.MemberPrivilege;
import in.co.dhdigital.missiontracker.entity.Mission;
import in.co.dhdigital.missiontracker.exception.NoSuchElementFoundException;
import in.co.dhdigital.missiontracker.exception.NotificationContactException;
import in.co.dhdigital.missiontracker.pojo.AvengerInfoPojo;
import in.co.dhdigital.missiontracker.pojo.AvengerPojo;
import in.co.dhdigital.missiontracker.pojo.AvengerStatusPojo;
import in.co.dhdigital.missiontracker.pojo.NotificationPojo;
import in.co.dhdigital.missiontracker.repository.AvengerRepository;
import in.co.dhdigital.missiontracker.repository.MissionRepositiory;
import in.co.dhdigital.missiontracker.utils.Enums.AvengerStatus;
import in.co.dhdigital.missiontracker.utils.Enums.Notification;
import in.co.dhdigital.missiontracker.utils.Enums.Privilege;
import in.co.dhdigital.missiontracker.utils.Enums.Status;

@Service
public class AvengerService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AvengerRepository avengerRepository;
	
	@Autowired
	private MissionRepositiory missionRepositiory;
	
	/*
	 * List all avenger with username, status filter capability
	 */
	public List<AvengerInfoPojo> getAvengers(String avengerName, AvengerStatus status) {
		List<Avenger> avengers = avengerRepository.findAll();
		List<AvengerInfoPojo> avengerInfoPojos = new ArrayList<>();
		if(avengerName!=null) {
			Avenger avenger = avengerRepository.findByUsername(avengerName).orElseThrow(()-> new NoSuchElementFoundException("We dont have any avenger with name "+avengerName));
			AvengerInfoPojo avengerInfoPojo = getAvengerInfoPojo(avenger);
			if(status!=null) {
				if(status==avengerInfoPojo.getStatus()) {
					avengerInfoPojos.add(avengerInfoPojo);
				}
			}else {
				avengerInfoPojos.add(avengerInfoPojo);
			}
		}else {
			for (Avenger avenger : avengers) {
				AvengerInfoPojo avengerInfoPojo = getAvengerInfoPojo(avenger);
				if(status!=null) {
					if(status==avengerInfoPojo.getStatus()) {
						avengerInfoPojos.add(avengerInfoPojo);
					}
				}else {
					avengerInfoPojos.add(avengerInfoPojo);
				}
			}
		}
		
		return avengerInfoPojos;
	}
	
	/*
	 * Helper methor to convert Avenger class to avengerinfo pojo
	 */
	public AvengerInfoPojo getAvengerInfoPojo(Avenger avenger) {
		AvengerInfoPojo avengerInfoPojo = new AvengerInfoPojo();
		avengerInfoPojo.setUsername(avenger.getUsername());
		avengerInfoPojo.setContact(avenger.getContact());
		avengerInfoPojo.setNotifiction(avenger.getNotifiction());
		avengerInfoPojo.setPrivileges(avenger.getPrivileges());
		avengerInfoPojo.setMissions(avenger.getMissions().stream().filter(a->a.getStatus()==Status.ASSIGN).collect(Collectors.toList()));
		avengerInfoPojo.setStatus(AvengerStatus.fromString(avengerInfoPojo.getMissions().size()));
		return avengerInfoPojo;
	}
	
	/*
	 * Save avenger information
	 */
	public Avenger saveAvenger(AvengerPojo avengerPojo) {
		Avenger avenger = new Avenger();
		avenger.setUsername(avengerPojo.getUsername());
		avenger.setPassword(passwordEncoder.encode(avengerPojo.getPassword()));
		MemberPrivilege memberPrivilege = new MemberPrivilege();
		memberPrivilege.setPrivilege(Privilege.VIEWAVENGER);
		avenger.setPrivilege(memberPrivilege);
		
		MemberPrivilege memberPrivilege1 = new MemberPrivilege();
		memberPrivilege1.setPrivilege(Privilege.VIEWMISSION);
		avenger.setPrivilege(memberPrivilege1);

		avenger.setContact(avengerPojo.getNotification().getContact());
		validateContact(avengerPojo.getNotification());
		
		avenger.setNotifiction(avengerPojo.getNotification().getType());
		return avengerRepository.save(avenger);
	}
	
	/*
	 * Validate notification contact
	 */
	public void validateContact(NotificationPojo notificationPojo) {
		String emailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
		String phoneRegex = "^\\d{10}$";
		 
		if(notificationPojo.getType()==Notification.EMAIL &&  !Pattern.compile(emailRegex).matcher(notificationPojo.getContact()).matches()) {
			throw new NotificationContactException("Invalid user email");
		}
		if(notificationPojo.getType()==Notification.SMS &&  !Pattern.compile(phoneRegex).matcher(notificationPojo.getContact()).matches()) {
			throw new NotificationContactException("Invalid user phone number (10 digit)");
		}
	}
	
	/*
	 * Get all mission of avenger based on username
	 */
	public List<Mission> getAvengerMissionByName(String name, String filter){
		return avengerRepository.findByUsername(name).orElseThrow(()-> new NoSuchElementFoundException("We dont have any avenger with name "+name)).getMissions();
	}


	/*
	 * Get avenger notification detail by user
	 */
	public NotificationPojo getAvengerNotificationByName(String name){
		Avenger avenger = avengerRepository.findByUsername(name).orElseThrow(()-> new NoSuchElementFoundException("We dont have any avenger with name "+name));
		NotificationPojo notificationPojo = new NotificationPojo();
		notificationPojo.setContact(avenger.getContact());
		notificationPojo.setType(avenger.getNotifiction());
		notificationPojo.setAvenger(name);
		return notificationPojo;
	}
	
	/*
	 * Update the notification information of avenger
	 */
	public NotificationPojo saveAvengerNotificationByName(NotificationPojo notificationPojo){
		Avenger avenger = avengerRepository.findByUsername(notificationPojo.getAvenger()).orElseThrow(()-> new NoSuchElementFoundException("We dont have any avenger with name "+notificationPojo.getAvenger()));
		avenger.setContact(notificationPojo.getContact());
		validateContact(notificationPojo);
		avenger.setNotifiction(notificationPojo.getType());
		avengerRepository.save(avenger);
		return notificationPojo;
	}

	/*
	 * Update avenger 
	 */
	public Avenger updateAvenger(@Valid AvengerPojo avengerPojo, String name) {
		Avenger avenger = avengerRepository.findByUsername(name).orElseThrow(()-> new NoSuchElementFoundException("We dont have any avenger with name "+name));
		avenger.setUsername(avengerPojo.getUsername());
		avenger.setPassword(passwordEncoder.encode(avengerPojo.getPassword()));
		Optional.ofNullable(avengerPojo.getPrivileges()).orElse(new ArrayList<>()).forEach(p->{
			MemberPrivilege memberPrivilege = new MemberPrivilege();
			memberPrivilege.setPrivilege(p);
			avenger.setPrivilege(memberPrivilege);
		});
		avenger.setContact(avengerPojo.getNotification().getContact());
		validateContact(avengerPojo.getNotification());
		avenger.setNotifiction(avengerPojo.getNotification().getType());
		return avengerRepository.save(avenger);
	}

	/*
	 * Get avenger information by avenger name 
	 */
	public AvengerStatusPojo getAvengerStatusByName(String name) {
		Avenger avenger = avengerRepository.findByUsername(name).orElseThrow(()-> new NoSuchElementFoundException("We dont have any avenger with name "+name));
		List<Mission> missions = missionRepositiory.findByAvengerAndStatus(avenger, Status.ASSIGN);
		AvengerStatusPojo statusPojo = new AvengerStatusPojo();
		statusPojo.setAvenger(avenger.getUsername());
		statusPojo.setStatus(AvengerStatus.fromString(missions.size()));
		
		return statusPojo;
	}

	public void deleteAvenger(String name) {
		// TODO Auto-generated method stub
		Avenger avenger = avengerRepository.findByUsername(name).orElseThrow(()-> new NoSuchElementFoundException("We dont have any avenger with name "+name));
		avengerRepository.deleteById(avenger.getId());
	}

}
