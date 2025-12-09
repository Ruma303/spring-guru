package demo.springboot.datajpa.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
