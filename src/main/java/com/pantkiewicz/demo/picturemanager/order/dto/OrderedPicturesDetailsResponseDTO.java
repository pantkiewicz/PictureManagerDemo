package com.pantkiewicz.demo.picturemanager.order.dto;

import com.google.common.collect.Lists;
import com.pantkiewicz.demo.picturemanager.common.validation.dto.ValidationErrorDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderedPicturesDetailsResponseDTO {
    private String result;
    private String statusMessage;
    private List<OrderedPictureDTO> orderedPictures = Lists.newArrayList();
    private List<ValidationErrorDTO> errors = Lists.newArrayList();
}
