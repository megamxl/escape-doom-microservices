package at.escapedoom.spring.components.errorconfig;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    // Getters & Setters
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
