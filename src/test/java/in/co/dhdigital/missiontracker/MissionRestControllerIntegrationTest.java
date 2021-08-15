package in.co.dhdigital.missiontracker;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.co.dhdigital.missiontracker.entity.Mission;
import in.co.dhdigital.missiontracker.repository.MissionRepositiory;

//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = MissionTrackerApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class MissionRestControllerIntegrationTest  {
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private MissionRepositiory missionRepositiory;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	void saveMissionWithUnauthorized() throws Exception {
		Mission mission = new Mission();
		String jsonRequest = objectMapper.writeValueAsString(mission);
		mvc.perform(post("/mission").content(jsonRequest).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized());
	}
	
//	@Test
//	void saveMissionWithBadRequest() throws Exception {
//		Mission mission = new Mission();
//		String jsonRequest = objectMapper.writeValueAsString(mission);
//		mvc.perform(post("/mission").content(jsonRequest).contentType(MediaType.APPLICATION_JSON) .with(SecurityMockMvcRequestPostProcessors.httpBasic("user", "secret"))).andExpect(status().isUnauthorized());
//	}
	
}
