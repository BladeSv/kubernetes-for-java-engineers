package by.mitrakhovich.songservice.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Song {
    @NotEmpty
    private String name;
    @NotEmpty
    private String artist;
    @NotEmpty
    private String album;
    @NotEmpty
    private String length;
    @Pattern(regexp = "\\d*")
    private String resourceId;
    @Pattern(regexp = "\\d{4}")
    private String year;
}
