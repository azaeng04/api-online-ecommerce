package api.online.ecommerce.commons;

public class ResponseBuilder<T> {
    public static <T> CustomApiResponse<T> buildResponse(ResponseMessages message, T data) {
        return new CustomApiResponse<>(
                message.getResponseCode(),
                message.getMessage(),
                message.getDescription(),
                message.getHttpStatus(),
                data);
    }
}
