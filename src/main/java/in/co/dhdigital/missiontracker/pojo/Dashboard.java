package in.co.dhdigital.missiontracker.pojo;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonView;

import in.co.dhdigital.missiontracker.entity.Mission;
import in.co.dhdigital.missiontracker.utils.Enums.AvengerStatus;
import in.co.dhdigital.missiontracker.utils.Enums.Status;
import in.co.dhdigital.missiontracker.view.JsonViews.UserJsonView;

public class Dashboard {

	@JsonView(UserJsonView.class)
	private Map<AvengerStatus, List<AvengerInfoPojo>>  avengerDashboards;
	
	@JsonView(UserJsonView.class)
	private Map<Status, List<Mission>> missionDashboards;
	
	public Map<AvengerStatus, List<AvengerInfoPojo>> getAvengerDashboards() {
		return avengerDashboards;
	}
	public void setAvengerDashboards(Map<AvengerStatus, List<AvengerInfoPojo>> avengerDashboards) {
		this.avengerDashboards = avengerDashboards;
	}
	public Map<Status, List<Mission>> getMissionDashboards() {
		return missionDashboards;
	}
	public void setMissionDashboards(Map<Status, List<Mission>> missionDashboards) {
		this.missionDashboards = missionDashboards;
	}
	
	
}
