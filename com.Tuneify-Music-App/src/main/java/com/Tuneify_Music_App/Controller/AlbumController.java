package com.Tuneify_Music_App.Controller;

import com.Tuneify_Music_App.Dto.AlbumRequest;
import com.Tuneify_Music_App.Service.AlbumService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<?> addAlbum(@RequestPart("request")
                                          String request,
                                      @RequestPart("file")MultipartFile file) {
        try{
            ObjectMapper mapper = new ObjectMapper();
          AlbumRequest albumRequest = mapper.readValue(request, AlbumRequest.class);
          albumRequest.setImageFile(file);
         return ResponseEntity.status(HttpStatus.CREATED).body(albumService.addAlbum(albumRequest));
        }catch (Exception e){
          return   ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
