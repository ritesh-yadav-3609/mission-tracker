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
import in.co.dhdigital.missiontracker.entity.Avenger;
import in.co.dhdigital.missiontracker.pojo.AvengerInfoPojo;
import in.co.dhdigital.missiontracker.pojo.AvengerPojo;
import in.co.dhdigital.missiontracker.pojo.AvengerStatusPojo;
import in.co.dhdigital.missiontracker.pojo.NotificationPojo;
import in.co.dhdigital.missiontracker.service.AvengerService;
import in.co.dhdigital.missiontracker.utils.Enums.AvengerStatus;
import in.co.dhdigital.missiontracker.view.JsonViews.UserJsonView;

@RestController
@RequestMapping("avenger")
public class AvengerController {

	@Autowired
	private AvengerService avengerService;
	
	/*
	 * List all avenger based with filter capability id username and status
	 */
	@GetMapping("")
	@JsonView(UserJsonView.class)
	public List<AvengerInfoPojo> getAvengers(@RequestParam(name = "avenger", required = false) String avengerName, @RequestParam(name = "status", required = false) AvengerStatus status){
		return avengerService.getAvengers(avengerName,status);
	}
	
	/*
	 * Add new avenger with notification details
	 */
	@PostMapping("")
	@JsonView(UserJsonView.class)
	public Avenger saveAvenger(@Valid @RequestBody AvengerPojo avengerPojo) {
		return avengerService.saveAvenger(avengerPojo);
	}
	
	/*
	 * Return the avenger status of username passed
	 */
	@GetMapping("/status")
	public AvengerStatusPojo getAvengerStatusByName(@RequestParam("avenger") String name){
		return avengerService.getAvengerStatusByName(name);
	}
	
	/*
	 * Get notification information of avenger
	 */
	@GetMapping("/notification")
	@JsonView(UserJsonView.class)
	public NotificationPojo getAvengerNotificationByName(@RequestParam("avenger") String name){
		return avengerService.getAvengerNotificationByName(name);
	}
	
	/*
	 * Update the notification details of avenger
	 */
	@PostMapping("/notification")
	@JsonView(UserJsonView.class)
	public NotificationPojo saveAvengerNotificationByName(@RequestBody NotificationPojo notificationPojo){
		return avengerService.saveAvengerNotificationByName(notificationPojo);
	}
	
	/*
	 * Update avenger detail by username
	 */
	@PutMapping("/{name}")
	@JsonView(UserJsonView.class)
	public Avenger updateAvenger(@Valid @RequestBody AvengerPojo avengerPojo, @PathVariable("name") String name) {
		return avengerService.updateAvenger(avengerPojo, name);
	}
	
	/*
	 * Delete avenger by username
	 */
	@DeleteMapping("/{name}")
	@JsonView(UserJsonView.class)
	public void deleteAvenger(@PathVariable("name") String name) {
		avengerService.deleteAvenger(name);
	}
	
}
