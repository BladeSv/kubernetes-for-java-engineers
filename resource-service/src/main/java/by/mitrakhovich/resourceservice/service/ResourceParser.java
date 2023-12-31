package by.mitrakhovich.resourceservice.service;

import by.mitrakhovich.resourceservice.model.Song;
import com.groupdocs.metadata.Metadata;
import com.groupdocs.metadata.core.ID3V2Tag;
import com.groupdocs.metadata.core.MP3RootPackage;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;

@Component
public class ResourceParser {
    private static final String NO_DATA = "No Data";

    public Song parseMp3(MultipartFile file, String resourceId) {

        ByteArrayInputStream inputStream;
        Song song;
        try {
            inputStream = new ByteArrayInputStream(file.getBytes());
            Metadata metadata = new Metadata(inputStream);
            MP3RootPackage rootPackageGeneric = metadata.getRootPackageGeneric();
            ID3V2Tag id3V2 = rootPackageGeneric.getID3V2();

            Optional<String> title = Optional.ofNullable(id3V2.getTitle());
            Optional<String> artist = Optional.ofNullable(id3V2.getArtist());
            Optional<String> album = Optional.ofNullable(id3V2.getAlbum());
            Optional<String> lengthInMilliseconds = Optional.ofNullable(id3V2.getLengthInMilliseconds());
            Optional<String> year = Optional.ofNullable(id3V2.getYear());

            song = Song.builder()
                    .name(title.orElse(NO_DATA))
                    .artist(artist.orElse(NO_DATA))
                    .album(album.orElse(NO_DATA))
                    .length(lengthInMilliseconds.orElse("0:00"))
                    .year(year.orElse("0000"))
                    .resourceId(resourceId)
                    .genre(artist.orElse(NO_DATA) + "_genre")
                    .build();

        } catch (IOException ex) {
            song = Song.builder()
                    .name(NO_DATA)
                    .artist(NO_DATA)
                    .album(NO_DATA)
                    .length("0:00")
                    .year("0000")
                    .resourceId(resourceId)
                    .genre("genre")
                    .build();
        }

        return song;
    }
}

