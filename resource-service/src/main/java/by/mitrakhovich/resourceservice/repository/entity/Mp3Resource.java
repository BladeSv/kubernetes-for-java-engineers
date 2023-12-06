package by.mitrakhovich.resourceservice.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Mp3Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Pattern(regexp = "^.*\\.mp3$", message = "Validation failed or request body is invalid MP3")
    private String fileName;

    @Lob
    private byte[] mp3Record;
}
