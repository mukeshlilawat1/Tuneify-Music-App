package com.Tuneify_Music_App.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SongRequest {

    private String id;
    private String name;
    private String description;


    @JsonProperty("Album")
    private String album;
    private MultipartFile audioFile;
    private MultipartFile imageFile;
}
