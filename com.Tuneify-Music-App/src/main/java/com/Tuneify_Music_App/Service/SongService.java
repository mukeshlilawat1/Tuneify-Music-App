package com.Tuneify_Music_App.Service;

import com.Tuneify_Music_App.Document.Song;
import com.Tuneify_Music_App.Dto.SongRequest;
import com.Tuneify_Music_App.Repository.SongRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;
    private final Cloudinary cloudinary;

    public Song addSong(SongRequest song) throws Exception {
        // Upload audio file
        Map<String, Object> audioUploadResult = cloudinary.uploader().upload(
                song.getAudioFile().getBytes(),
                ObjectUtils.asMap("resource_type", "video") // <-- important: video gives duration
        );

        // Upload image file
        Map<String, Object> imageUploadResult = cloudinary.uploader().upload(
                song.getImageFile().getBytes(),
                ObjectUtils.asMap("resource_type", "image")
        );

        // Get duration
        Double durationSeconds = (Double) audioUploadResult.get("duration");
        String duration = formatDuration(durationSeconds);

        Song newSong = Song.builder()
                .name(song.getName())
                .description(song.getDescription())
                .album(song.getAlbum())
                .image(imageUploadResult.get("secure_url").toString())
                .file(audioUploadResult.get("secure_url").toString())
                .duration(duration)
                .build();

        return songRepository.save(newSong);
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public Boolean removeSong(String id) {
        return songRepository.findById(id).map(song -> {
            songRepository.delete(song);
            return true;
        }).orElse(false);
    }

    private String formatDuration(Double durationSeconds) {
        if (durationSeconds == null) return "0:00";
        int minutes = (int) (durationSeconds / 60);
        int seconds = (int) (durationSeconds % 60);
        return String.format("%02d:%02d", minutes, seconds);
    }
}
