package response;

import java.util.Date;

import org.springframework.http.HttpStatus;

import com.example.demo.dto.ResponseBodyDTO;

public class ResponseGenerator<T> {

	public ResponseGenerator() {
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> ResponseBodyDTO<T> generateResponse(T response, HttpStatus status, Date startCallDate) {
		return new ResponseBodyDTO(response, startCallDate, new Date(), status);
	}
}
