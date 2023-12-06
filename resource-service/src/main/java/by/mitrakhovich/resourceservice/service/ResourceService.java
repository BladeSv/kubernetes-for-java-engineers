package by.mitrakhovich.resourceservice.service;

import by.mitrakhovich.resourceservice.exception.NotFoundEntityException;
import by.mitrakhovich.resourceservice.model.Song;
import by.mitrakhovich.resourceservice.repository.ResourceRepository;
import by.mitrakhovich.resourceservice.repository.entity.Mp3Resource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
public class ResourceService {

    @Autowired
    private ResourceRepository repository;
    @Autowired
    private ResourceParser resourceParser;

    @Value("${song.service.url}")
    private String songServiceUrl;

    public String createResource(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        byte[] resourceBytes = file.getBytes();
        Mp3Resource mp3Resource = Mp3Resource.builder().mp3Record(resourceBytes).fileName(originalFilename).build();
        Mp3Resource savedMp3Resource = repository.save(mp3Resource);
        String responseMessage = String.format("{\"id\": %s}", savedMp3Resource.getId());
        Song song = resourceParser.parseMp3(file, savedMp3Resource.getId().toString());
        boolean isSuccessful = sendSongMetadata(song);

        if (!isSuccessful) {
            repository.deleteById(savedMp3Resource.getId());
            responseMessage = "Can\'t save song resource";
        }

        return responseMessage;
    }

    public Mp3Resource getResourceById(Integer id) {
        Optional<Mp3Resource> mp3ResourceOptional = repository.findById(id);
        Mp3Resource mp3Resource = mp3ResourceOptional.orElseThrow(() -> new NotFoundEntityException("The resource with the specified id does not exist"));

        return mp3Resource;
    }

    public String removeResourcesByIds(List<Integer> ids) {
        List<Integer> existIds = ids.stream().filter(id -> repository.existsById(id)).toList();
        repository.deleteAllById(existIds);

        return String.format("{\"ids\": %s}", existIds);
    }

    private boolean sendSongMetadata(Song song) {
        ObjectMapper objectMapper = new ObjectMapper();
        String songJson;
        try {
            songJson = objectMapper.writeValueAsString(song);
        } catch (JsonProcessingException e) {
            return false;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity(songJson, headers);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(songServiceUrl, request, String.class);
        return response != null && !response.isBlank();
    }
}
