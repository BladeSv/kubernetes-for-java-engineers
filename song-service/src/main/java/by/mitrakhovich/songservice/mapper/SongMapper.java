package by.mitrakhovich.songservice.mapper;

import by.mitrakhovich.songservice.model.Song;
import by.mitrakhovich.songservice.repositry.entity.SongDao;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface SongMapper {
    Song songDaoToSong(SongDao songDao);

    SongDao songToSongDao(Song song);
}
