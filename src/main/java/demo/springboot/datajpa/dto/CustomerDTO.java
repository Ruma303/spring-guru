package demo.springboot.datajpa.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomerDTO {
    private UUID id;
    private String name;
    private Integer version;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
