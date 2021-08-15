package in.co.dhdigital.missiontracker.exception;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -7858869558953243875L;
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		ExceptionMessage message = new ExceptionMessage();
		message.setTimeStamp(new Date().getTime());
		message.setStatus(HttpStatus.UNAUTHORIZED);
		message.setError("Error page");
		message.setMessage("Credentials are not matched");
		String responseMsg = mapper.writeValueAsString(message);
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().write(responseMsg);
	}

	
}
