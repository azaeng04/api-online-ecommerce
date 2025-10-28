package api.online.ecommerce.commons;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseMessages {

    GENERAL_SUCCESS_MESSAGE("Success", "Successfully returned response", HttpStatus.OK, 12345),
    GENERAL_CREATED_MESSAGE("Created", "Created object successfully", HttpStatus.CREATED, 123432),
    GENERAL_SYSTEM_ERROR("Error", "General system error", HttpStatus.INTERNAL_SERVER_ERROR, 63721),

    PRODUCT_CREATED_MESSAGE("Created", "Product created successfully", HttpStatus.CREATED, 6326763),

    CUSTOMER_NOT_FOUND("Not found", "Customer not found", HttpStatus.NOT_FOUND, 84891);

    private String message;
    private String description;
    private HttpStatus httpStatus;
    private int responseCode;

    ResponseMessages(String message, String description, HttpStatus httpStatus, int responseCode) {
        this.message = message;
        this.description = description;
        this.httpStatus = httpStatus;
        this.responseCode = responseCode;
    }
}
