package by.mitrakhovich.resourceservice.controller;


import by.mitrakhovich.resourceservice.repository.entity.Mp3Resource;
import by.mitrakhovich.resourceservice.service.ResourceService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/resources")
public class ResourceController {
    private ResourceService resourceService;

    @PostMapping
    public ResponseEntity createResource(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok().body(resourceService.createResource(file));
    }


    @GetMapping(value = "/{id}", produces = "audio/mp3")
    public ResponseEntity<ByteArrayResource> getResourceById(@PathVariable Integer id) {

        Mp3Resource mp3Resource = resourceService.getResourceById(id);

        ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                .filename(mp3Resource.getFileName() + ".mp3")
                .build();

        ByteArrayResource resource = new ByteArrayResource(mp3Resource.getMp3Record());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString())
                .header(HttpHeaders.CACHE_CONTROL, "no-cache")
                .body(resource);
    }

    @DeleteMapping
    public ResponseEntity removeResources(@RequestParam("id") List<Integer> ids) {
        return ResponseEntity.ok().body(resourceService.removeResourcesByIds(ids));
    }
}
