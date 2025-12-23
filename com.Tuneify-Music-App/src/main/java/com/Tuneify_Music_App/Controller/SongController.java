package com.Tuneify_Music_App.Controller;

import com.Tuneify_Music_App.Document.Song;
import com.Tuneify_Music_App.Dto.SongListResponse;
import com.Tuneify_Music_App.Dto.SongRequest;
import com.Tuneify_Music_App.Repository.SongRepository;
import com.Tuneify_Music_App.Service.SongService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.runtime.ObjectMethods;

@RestController
@RequestMapping("api/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongRepository songRepository;
    private final SongService songService;

    @PostMapping
    public ResponseEntity<?> addSong(@RequestPart("request") String requestString,
                                     @RequestPart("audio")MultipartFile audioFile,
                                     @RequestPart("image") MultipartFile imageFile) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
           SongRequest songRequest = objectMapper.readValue(requestString, SongRequest.class);
           songRequest.setImageFile(imageFile);
           songRequest.setAudioFile(audioFile);
//           songService.addSong(songRequest);
           return ResponseEntity.status(HttpStatus.CREATED).body(songService.addSong(songRequest));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listSongs() {
        try
        {
            return ResponseEntity.ok(songService.getAllSongs());
        }catch (Exception e) {
            return ResponseEntity.ok(new SongListResponse(false, null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> RemoveSong(@PathVariable String id) {
        try {
            Boolean removed = songService.removeSong(id);
            if (removed) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
