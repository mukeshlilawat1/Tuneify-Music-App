package com.Tuneify_Music_App.Repository;

import com.Tuneify_Music_App.Document.Album;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlbumRepository extends MongoRepository<Album, String> {

}
