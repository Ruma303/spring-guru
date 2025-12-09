package demo.springboot.datajpa.model;

import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @UuidGenerator
    @GeneratedValue
    @Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID id;

    private String name;

    @Version
    private Integer version;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
