package api.online.ecommerce.commons;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class CustomApiResponse<T> {
    private int responseCode;
    private String message;
    private String description;
    private HttpStatus httpStatus;
    private T data;
}
