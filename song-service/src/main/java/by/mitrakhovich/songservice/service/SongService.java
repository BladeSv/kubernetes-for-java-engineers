package by.mitrakhovich.songservice.service;

import by.mitrakhovich.songservice.exception.NotFoundSongException;
import by.mitrakhovich.songservice.mapper.SongMapper;
import by.mitrakhovich.songservice.model.Song;
import by.mitrakhovich.songservice.repositry.SongRepository;
import by.mitrakhovich.songservice.repositry.entity.SongDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SongService {
    private SongRepository repository;
    private SongMapper mapper;

    public String createSongRecord(Song song) {
        SongDao songDao = mapper.songToSongDao(song);
        SongDao savedSong = repository.save(songDao);

        return String.format("{\"id\": %s}", savedSong.getId());
    }

    public Song getSongById(Integer id) {
        SongDao songDao = repository.findById(id).orElseThrow(() -> new NotFoundSongException("The song metadata with the specified id does not exist"));
        return mapper.songDaoToSong(songDao);
    }

    public String removeSongsByIds(List<Integer> ids) {
        List<Integer> existSongs = ids.stream().filter(id -> repository.existsById(id)).toList();
        repository.deleteAllById(existSongs);

        return String.format("{\"ids\": %s}", existSongs);
    }
}
