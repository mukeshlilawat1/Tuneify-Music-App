package com.Tuneify_Music_App.Dto;

import com.Tuneify_Music_App.Document.Album;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlbumListResponse {
    private boolean success;
    private List<Album> albumList;

}
