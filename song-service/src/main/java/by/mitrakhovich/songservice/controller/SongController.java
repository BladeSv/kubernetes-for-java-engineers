package by.mitrakhovich.songservice.controller;

import by.mitrakhovich.songservice.model.Song;
import by.mitrakhovich.songservice.service.SongService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/songs")
public class SongController {
    private SongService songService;

    @PostMapping
    public ResponseEntity createSong(@Valid @RequestBody Song song) {
        String songRecordId = songService.createSongRecord(song);
        return ResponseEntity.ok().body(songRecordId);
    }

    @GetMapping("/{id}")
    public ResponseEntity getSongById(@PathVariable Integer id) {
        Song songById = songService.getSongById(id);
        return ResponseEntity.ok().body(songById);
    }

    @DeleteMapping
    public ResponseEntity removeSongByIds(@RequestParam("id") List<Integer> ids) {
        String removedSongsIds = songService.removeSongsByIds(ids);
        return ResponseEntity.ok().body(removedSongsIds);
    }
}
