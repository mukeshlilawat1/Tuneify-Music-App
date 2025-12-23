package com.Tuneify_Music_App.Controller;

import com.Tuneify_Music_App.Dto.AlbumListResponse;
import com.Tuneify_Music_App.Dto.AlbumRequest;
import com.Tuneify_Music_App.Service.AlbumService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/albums")
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    @PostMapping()
    public ResponseEntity<?> addAlbum(@RequestPart("request") String request,
                                      @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            AlbumRequest albumRequest = mapper.readValue(request, AlbumRequest.class);

            if (file != null) {
                albumRequest.setImageFile(file);
            } else {
                System.out.println("Warning: file is null");
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(albumService.addAlbum(albumRequest));
        } catch (Exception e) {
            e.printStackTrace(); // Full stack trace
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<?> listAlbums() {
        try
        {
          return ResponseEntity.ok(albumService.getAllAlbums());
        }catch (Exception e) {
            return ResponseEntity.ok(new AlbumListResponse(false, null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAlbum(@PathVariable String id) {
        try {
          boolean removed =  albumService.removeAlbum(id);

          if (removed) {
              return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
          } else {
              return ResponseEntity.badRequest().build();
          }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
