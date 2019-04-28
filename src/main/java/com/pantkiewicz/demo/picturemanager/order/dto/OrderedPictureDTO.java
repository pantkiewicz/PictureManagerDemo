package com.pantkiewicz.demo.picturemanager.order.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderedPictureDTO {
    private String eventID;
    private String eventName;
    private String pictureID;
    private String pictureName;
    private String count;
}
