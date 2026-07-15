package jiwon.financetracker.exception;

import jiwon.financetracker.dto.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<String>> handleException(Exception e) {
        String message = "Error occurred while processing request";
        HttpStatusCode status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (e instanceof ResponseStatusException responseStatusException) {
            message = responseStatusException.getReason();
            status = responseStatusException.getStatusCode();
        }

        BaseResponse<String> response = new BaseResponse<>(status.value(), message, null);
        return ResponseEntity.status(status).body(response);
    }

}
