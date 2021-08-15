package in.co.dhdigital.missiontracker.exception;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

//@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	
	/*
	 * Handle value not found exception
	 */
	@ExceptionHandler({NoSuchElementFoundException.class})
	public ResponseEntity<ExceptionMessage> handleElementNotFoundException(NoSuchElementFoundException ex){
		ExceptionMessage message = new ExceptionMessage();
		message.setTimeStamp(new Date().getTime());
		message.setStatus(HttpStatus.BAD_REQUEST);
		message.setMessage(ex.getMessage());
		message.setError("Bad request");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	}
	
//	@ResponseStatus(code = HttpStatus.NOT_FOUND)
//	public ResponseEntity<ExceptionMessage> handleUrlNotFoundException(Exception ex){
//		ExceptionMessage message = new ExceptionMessage();
//		message.setTimeStamp(LocalDateTime.now());
//		message.setStatus(HttpStatus.BAD_REQUEST);
//		message.setMessage(ex.getMessage());
//		message.setError("Bad request");
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
//	}
	
	/*
	 * Handle mission condition exception
	 */
	@ExceptionHandler(MissionOutOfBoundException.class)
	public ResponseEntity<ExceptionMessage> handleMissionOutOfBoundException(MissionOutOfBoundException ex){
		ExceptionMessage message = new ExceptionMessage();
		message.setTimeStamp(new Date().getTime());
		message.setStatus(HttpStatus.BAD_REQUEST);
		message.setMessage(ex.getMessage());
		message.setError("Bad request");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	}
	
	@ExceptionHandler(MissionInvalidStatusException.class)
	public ResponseEntity<ExceptionMessage> handleMissionInvalidStatusException(MissionInvalidStatusException ex){
		ExceptionMessage message = new ExceptionMessage();
		message.setTimeStamp(new Date().getTime());
		message.setStatus(HttpStatus.BAD_REQUEST);
		message.setMessage(ex.getMessage());
		message.setError("Bad request");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	}
	
	@ExceptionHandler(NotificationContactException.class)
	public ResponseEntity<ExceptionMessage> handleNotificationContactException(NotificationContactException ex){
		ExceptionMessage message = new ExceptionMessage();
		message.setTimeStamp(new Date().getTime());
		message.setStatus(HttpStatus.BAD_REQUEST);
		message.setMessage(ex.getMessage());
		message.setError("Bad request");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	}
	
//	@Override
//	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
//			HttpStatus status, WebRequest request) {
//		ExceptionMessage message = new ExceptionMessage();
//		message.setTimeStamp(new Date().getTime());
//		message.setStatus(status.name());
//		message.setMessage("");
//		message.setErrorDetail(ex.getMessage());
//		return ResponseEntity.status(status).body(message);
//	}
//	
//	
//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		ExceptionMessage message = new ExceptionMessage();
//		message.setTimeStamp(new Date().getTime());
//		message.setStatus(status.name());
//		message.setMessage("");
//		message.setErrorDetail(ex.getMessage());
//		return ResponseEntity.status(status).body(message);
//	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionMessage> handleGlobalException(Exception ex){
		
		ExceptionMessage message = new ExceptionMessage();
		message.setTimeStamp(new Date().getTime());
		message.setStatus(HttpStatus.BAD_REQUEST);
		message.setError("Error page");
		message.setMessage(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	}
	
	 @ExceptionHandler(NoHandlerFoundException.class)
	 public ResponseEntity<ExceptionMessage> handleError404(HttpServletRequest request, Exception ex)   {
		ExceptionMessage message = new ExceptionMessage();
		message.setTimeStamp(new Date().getTime());
		message.setStatus(HttpStatus.NOT_FOUND);
		message.setError("Error page");
		message.setMessage("We dont have this page");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	 }
	

}
