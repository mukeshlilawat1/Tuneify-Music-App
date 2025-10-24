package com.Tuneify_Music_App.Service;

import com.Tuneify_Music_App.Document.Album;
import com.Tuneify_Music_App.Dto.AlbumListResponse;
import com.Tuneify_Music_App.Dto.AlbumRequest;
import com.Tuneify_Music_App.Repository.AlbumRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final Cloudinary cloudinary;
    private final AlbumRepository albumRepository;

    public Album addAlbum(AlbumRequest albumRequest) throws IOException {
        Map imageUploadResult = cloudinary.uploader().upload(albumRequest.getImageFile().getBytes(),
                ObjectUtils.asMap("resource_type", "image"));

        Album newAlbum = Album.builder().name(albumRequest.getName()).description(albumRequest.getDescription())
                .background_colour(albumRequest
                        .getBackground_color())
                .image_url(imageUploadResult.get("secure_url").toString()).build();
        return albumRepository.save(newAlbum);
    }


    public AlbumListResponse getAllAlbums() {
        return new AlbumListResponse(true, albumRepository.findAll());
    }

    public Boolean removeAlbum(String id){
       Album existingAlbum = albumRepository.findById(id).orElseThrow(() -> new RuntimeException("Album not found"));
       albumRepository.delete(existingAlbum);
       return true;
    }
}
