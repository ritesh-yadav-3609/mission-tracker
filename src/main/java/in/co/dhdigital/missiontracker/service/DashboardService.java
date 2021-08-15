package in.co.dhdigital.missiontracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.co.dhdigital.missiontracker.entity.Avenger;
import in.co.dhdigital.missiontracker.entity.Mission;
import in.co.dhdigital.missiontracker.pojo.AvengerInfoPojo;
import in.co.dhdigital.missiontracker.pojo.Dashboard;
import in.co.dhdigital.missiontracker.repository.AvengerRepository;
import in.co.dhdigital.missiontracker.repository.MissionRepositiory;
import in.co.dhdigital.missiontracker.utils.Enums.AvengerStatus;
import in.co.dhdigital.missiontracker.utils.Enums.Status;

@Service
public class DashboardService {

	@Autowired
	private AvengerRepository avengerRepository;
	
	@Autowired
	private MissionRepositiory missionRepositiory;
	
	@Autowired
	private AvengerService avengerService;
	
	/*
	 * List all avenger with username, status filter capability
	 */
	public Dashboard getDashBoardDetails() {
		Dashboard dashboard = new Dashboard();
		List<Avenger> avengers = avengerRepository.findAll();
		List<AvengerInfoPojo> avengerInfoPojos = new ArrayList<>();
		System.out.println(avengers);
		for (Avenger avenger : avengers) {
			AvengerInfoPojo avengerInfoPojo = avengerService.getAvengerInfoPojo(avenger);
			avengerInfoPojos.add(avengerInfoPojo);
		}
		System.out.println("avengers");
		System.out.println(avengerInfoPojos);
		Map<AvengerStatus, List<AvengerInfoPojo>> avengerDashboards = avengerInfoPojos.stream().collect(Collectors.groupingBy(AvengerInfoPojo::getStatus));
		List<Mission> missions = missionRepositiory.findAll();
		Map<Status, List<Mission>> missionDashboards = missions.stream().collect(Collectors.groupingBy(Mission::getStatus));
		dashboard.setAvengerDashboards(avengerDashboards);
		System.out.println(avengerDashboards);
		dashboard.setMissionDashboards(missionDashboards);
		return dashboard;
	}
	

}
