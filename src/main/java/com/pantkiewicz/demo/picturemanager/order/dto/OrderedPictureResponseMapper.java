package com.pantkiewicz.demo.picturemanager.order.dto;

import com.google.common.collect.Lists;
import com.pantkiewicz.demo.picturemanager.common.validation.ValidationErrorMapper;
import com.pantkiewicz.demo.picturemanager.order.repository.OrderedPicturesCountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.List;

@Component
public class OrderedPictureResponseMapper {
    private static final String OK_RESULT = "OK";
    private static final String ERROR_RESULT = "NG";
    private static final String FAILED_VALIDATION_MESSAGE = "Input parameters validation failed";

    @Autowired
    ValidationErrorMapper validationErrorMapper;

    public OrderedPicturesDetailsResponseDTO buildSuccessfulResponse(List<OrderedPicturesCountDTO> results) {
        OrderedPicturesDetailsResponseDTO response = new OrderedPicturesDetailsResponseDTO();
        response.setResult(OK_RESULT);
        response.setOrderedPictures(Lists.transform(results, this::mapToOrderedPicture));

        return response;
    }

    public OrderedPicturesDetailsResponseDTO buildErrorResponse(BindingResult result) {
        OrderedPicturesDetailsResponseDTO response = new OrderedPicturesDetailsResponseDTO();
        response.setResult(ERROR_RESULT);
        response.setStatusMessage(FAILED_VALIDATION_MESSAGE);
        response.setErrors(Lists.transform(result.getAllErrors(), validationErrorMapper::mapToValidationErrorDTO));
        return response;
    }

    private OrderedPictureDTO mapToOrderedPicture(OrderedPicturesCountDTO resultDTO) {
        OrderedPictureDTO picture = new OrderedPictureDTO();
        picture.setEventID(resultDTO.getEventId().toString());
        picture.setEventName(resultDTO.getEventName());
        picture.setPictureID(resultDTO.getPhotoId().toString());
        picture.setPictureName(resultDTO.getPhotoName());
        picture.setCount(resultDTO.getPictureCount().toString());
        return picture;
    }
}
