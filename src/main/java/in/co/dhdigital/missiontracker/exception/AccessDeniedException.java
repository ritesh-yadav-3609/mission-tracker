package in.co.dhdigital.missiontracker.exception;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AccessDeniedException implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			org.springframework.security.access.AccessDeniedException accessDeniedException)
			throws IOException, ServletException {
		ObjectMapper mapper = new ObjectMapper();
		ExceptionMessage message = new ExceptionMessage();
		message.setTimeStamp(new Date().getTime());
		message.setStatus(HttpStatus.UNAUTHORIZED);
		message.setError("Error page");
		message.setMessage("You are unauthorized to access this service");
		String responseMsg = mapper.writeValueAsString(message);
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().write(responseMsg);
		
	}
	
}