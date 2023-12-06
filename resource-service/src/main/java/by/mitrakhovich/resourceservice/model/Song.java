package by.mitrakhovich.resourceservice.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Song {
    private String name;
    private String artist;
    private String album;
    private String length;
    private String resourceId;
    private String year;
}
