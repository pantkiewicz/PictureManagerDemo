package com.pantkiewicz.demo.picturemanager.picture.repository;

import com.pantkiewicz.demo.picturemanager.picture.model.Picture;
import org.springframework.data.repository.CrudRepository;

public interface PictureRepository extends CrudRepository<Picture, Integer> {
}
