package in.co.dhdigital.missiontracker.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;
import in.co.dhdigital.missiontracker.entity.Mission;
import in.co.dhdigital.missiontracker.pojo.MissionPojo;
import in.co.dhdigital.missiontracker.pojo.StatusPojo;
import in.co.dhdigital.missiontracker.service.MissionService;
import in.co.dhdigital.missiontracker.utils.Enums.Status;
import in.co.dhdigital.missiontracker.view.JsonViews.MissionJsonView;

@RestController
@RequestMapping("mission")
public class MissionController {

	@Autowired
	private MissionService missionService;
	
	/*
	 * List all mission based with filter capability id username and status
	 * 
	 */
	@GetMapping("")
	@JsonView(MissionJsonView.class)
	public List<Mission> getAllMession(@RequestParam(name = "avenger", required = false) String avengerName, @RequestParam(name = "status", required = false) Status status){
		return missionService.getAllMissions(avengerName, status);
	}
	
	/*
	 * Add new mission for a avenger
	 */
	@PostMapping("")
	@JsonView(MissionJsonView.class)
	public Mission saveMission(@Valid @RequestBody MissionPojo missionPojo){
		return missionService.saveMission(missionPojo);
	}
	
	/*
	 * Update mission details
	 */
	@PutMapping("/{id}")
	@JsonView(MissionJsonView.class)
	public Mission updateMission(@Valid @RequestBody MissionPojo missionPojo, @PathVariable("id") Long id) {
		return missionService.updateMission(missionPojo,id);
	}
	
	/*
	 * Delete mission
	 */
	@DeleteMapping("/{id}")
	@JsonView(MissionJsonView.class)
	public Object deleteMission(@PathVariable("id") Long id) {
		return missionService.deleteMission(id);
	}
	
	/*
	 * Update the mission status
	 */
	@PostMapping("/status")
	@JsonView(MissionJsonView.class)
	public StatusPojo updateStatus(@Valid @RequestBody StatusPojo statusPojo) {
		return missionService.updateStatus(statusPojo);
	}
	
	
}
