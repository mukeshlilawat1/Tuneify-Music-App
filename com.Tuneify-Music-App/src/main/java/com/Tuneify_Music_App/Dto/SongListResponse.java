package com.Tuneify_Music_App.Dto;

import com.Tuneify_Music_App.Document.Song;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SongListResponse {
    private Boolean success;
    private List<Song> songs;

}
