package in.co.dhdigital.missiontracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.co.dhdigital.missiontracker.entity.Avenger;
import in.co.dhdigital.missiontracker.entity.Mission;
import in.co.dhdigital.missiontracker.exception.MissionInvalidStatusException;
import in.co.dhdigital.missiontracker.exception.MissionOutOfBoundException;
import in.co.dhdigital.missiontracker.exception.NoSuchElementFoundException;
import in.co.dhdigital.missiontracker.pojo.MissionPojo;
import in.co.dhdigital.missiontracker.pojo.StatusPojo;
import in.co.dhdigital.missiontracker.repository.AvengerRepository;
import in.co.dhdigital.missiontracker.repository.MissionRepositiory;
import in.co.dhdigital.missiontracker.utils.Enums.Status;
import in.co.dhdigital.missiontracker.utils.NotificationManager;

@Service
public class MissionService {

//	private final CapabilityManagement capabilityManagement;
	
	@Autowired
	private MissionRepositiory missionRepositiory;
	
	@Autowired
	private AvengerRepository avengerRepository;
	
	@Autowired
	private NotificationManager notificationManager;
	
	/*
	 * List all mission with filter capability
	 */
	public List<Mission> getAllMissions(String avengerName, Status status){
	
		if(avengerName!=null && status!=null) {
			Avenger avenger = avengerRepository.findByUsername(avengerName).orElseThrow(()-> new NoSuchElementFoundException("We dont have any avenger with name "+avengerName));;
			return missionRepositiory.findByAvengerAndStatus(avenger, status);
		}else if(status!=null) {
			return missionRepositiory.findByStatus(status);
		}else if(avengerName!=null) {
			Avenger avenger = avengerRepository.findByUsername(avengerName).orElseThrow(()-> new NoSuchElementFoundException("We dont have any avenger with name "+avengerName));;
			return missionRepositiory.findByAvenger(avenger);
		}
		return missionRepositiory.findAll();
	}
	
	/*
	 * Save mission 
	 */
	public Mission saveMission(MissionPojo missionPojo){
		Mission mission = new Mission();
		mission.setName(missionPojo.getName());
		mission.setDescription(missionPojo.getDescription());
		mission.setStatus(Status.ASSIGN);
		Avenger avenger = avengerRepository.findByUsername(missionPojo.getAvengerName()).orElseThrow(()-> new NoSuchElementFoundException("We dont have any avenger with name "+missionPojo.getAvengerName()));
		List<Mission> missions = missionRepositiory.findByAvengerAndStatus(avenger, Status.ASSIGN);
		if(missions.size()>=2) {
			throw new MissionOutOfBoundException(avenger.getUsername()+" already have two mission.");
		}
		mission.setAvenger(avenger);
		notificationManager.sendNotification(mission.getAvenger(), "New mission is assign to you.");
		return missionRepositiory.save(mission);
	}

	/*
	 * Get mission detain baesd on mission id
	 */
	public Mission getMissionById(Long id) {
		return missionRepositiory.findById(id).orElseThrow(()-> new NoSuchElementFoundException("We dont have any mission with id "+id));
	}

	/*
	 * Update status of mission status
	 */
	public StatusPojo updateStatus(StatusPojo statusPojo) {
		Mission mission = missionRepositiory.findById(statusPojo.getId()).orElseThrow(()-> new NoSuchElementFoundException("We dont have any mission with id "+statusPojo.getId()));
		if(mission.getStatus()== Status.COMPLETED) {
			throw new MissionInvalidStatusException("Mission is already completed");
		}
		missionRepositiory.updateStatus(statusPojo.getStatus(), statusPojo.getId());
		notificationManager.sendNotification(mission.getAvenger(), "Status is updated to "+statusPojo.getStatus());
		return statusPojo;
	}

	/*
	 * Update mission details
	 */
	public Mission updateMission(MissionPojo missionPojo, Long id) {
		Mission mission = missionRepositiory.findById(id).orElseThrow(()-> new NoSuchElementFoundException("We dont have any mission with id "+id));
		if(mission.getStatus()== Status.COMPLETED) {
			throw new MissionInvalidStatusException("Mission is already completed");
		}
		mission.setName(missionPojo.getName());
		mission.setDescription(missionPojo.getDescription());
		mission.setStatus(Status.ASSIGN);
		return missionRepositiory.save(mission);
	}

	/*
	 * Delete mission
	 */
	public Object deleteMission(Long id) {
		missionRepositiory.deleteById(id);
		return null;
	}

}
