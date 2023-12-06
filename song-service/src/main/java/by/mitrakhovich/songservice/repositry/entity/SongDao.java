package by.mitrakhovich.songservice.repositry.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class SongDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //    @NotEmpty
    private String name;
    //    @NotEmpty
    private String artist;
    //    @NotEmpty
    private String album;
    //    @NotEmpty
    private String length;
    //    @Pattern(regexp = "\\d*")
    private String resourceId;
    //    @Pattern(regexp = "\\d{4}")
    private String year;
}
