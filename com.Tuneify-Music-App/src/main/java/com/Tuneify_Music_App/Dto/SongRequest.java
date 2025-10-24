package com.Tuneify_Music_App.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SongRequest {

    private String id;
    private String name;
    private String description;
    private String Album;
    private MultipartFile audioFile;
    private MultipartFile imageFile;
}
