package in.co.dhdigital.missiontracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import in.co.dhdigital.missiontracker.pojo.AvengerInfoPojo;
import in.co.dhdigital.missiontracker.pojo.Dashboard;
import in.co.dhdigital.missiontracker.service.AvengerService;
import in.co.dhdigital.missiontracker.service.DashboardService;
import in.co.dhdigital.missiontracker.utils.Enums.AvengerStatus;
import in.co.dhdigital.missiontracker.view.JsonViews.UserJsonView;

@RestController
@RequestMapping("dashboard")
public class DashboardContoller {

	@Autowired
	private DashboardService dashboardService;
	
	/*
	 * List all avenger based with filter capability id username and status
	 */
	@GetMapping("")
	@JsonView(UserJsonView.class)
	public Dashboard getAvengers(){
		return dashboardService.getDashBoardDetails();
	}
}
