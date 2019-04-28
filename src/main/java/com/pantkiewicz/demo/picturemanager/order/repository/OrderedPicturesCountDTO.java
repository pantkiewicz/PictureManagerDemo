package com.pantkiewicz.demo.picturemanager.order.repository;

public interface OrderedPicturesCountDTO {
    Long getEventId();
    String getEventName();
    Long getPhotoId();
    String getPhotoName();
    Integer getPictureCount();
}
