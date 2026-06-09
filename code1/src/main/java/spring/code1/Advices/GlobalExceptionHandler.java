package spring.code1.Advices;


import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
@ExceptionHandler(NoSuchElementException.class)
public ResponseEntity<ApiResponse<?>> handleResourceNotFound(NoSuchElementException e)
{
    ApiError error = ApiError.builder()
            .status(HttpStatus.NOT_FOUND)
            .message(e.getMessage())
            .build();

    return buildApiResponseError(error);
}



@ExceptionHandler(Exception.class)
public ResponseEntity<ApiResponse<?>> handleInternalError(Exception e)
{
    ApiError error = ApiError.builder()
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .message(e.getMessage())
            .build();

    return buildApiResponseError(error);
}



@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ApiResponse<?>> handleInputValidationErrors(MethodArgumentNotValidException exception) {
    List<String> errors = exception
            .getBindingResult()
            .getAllErrors()
            .stream()
            .map(error -> error.getDefaultMessage())
            .collect(Collectors.toList());

    ApiError apiError = ApiError.builder()
            .status(HttpStatus.BAD_REQUEST)
            .message("Input validation failed")
            .subErrors(errors)
            .build();
    return buildApiResponseError(apiError);
}

public ResponseEntity<ApiResponse<?>> buildApiResponseError(ApiError apiError)
{
    return new ResponseEntity<>(new ApiResponse<>(apiError), apiError.getStatus());
}

}
