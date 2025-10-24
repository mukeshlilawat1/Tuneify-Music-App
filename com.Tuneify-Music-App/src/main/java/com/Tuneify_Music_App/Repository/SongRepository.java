package com.Tuneify_Music_App.Repository;

import com.Tuneify_Music_App.Document.Song;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SongRepository extends MongoRepository<Song, String> {

}
