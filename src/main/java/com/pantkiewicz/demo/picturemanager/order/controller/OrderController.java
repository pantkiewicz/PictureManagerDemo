package com.pantkiewicz.demo.picturemanager.order.controller;

import com.pantkiewicz.demo.picturemanager.order.dto.DateFormatValidator;
import com.pantkiewicz.demo.picturemanager.order.dto.OrderedPicturesDetailsRequestDTO;
import com.pantkiewicz.demo.picturemanager.order.dto.OrderedPicturesDetailsResponseDTO;
import com.pantkiewicz.demo.picturemanager.order.dto.OrderedPictureResponseMapper;
import com.pantkiewicz.demo.picturemanager.order.repository.OrderRepository;
import com.pantkiewicz.demo.picturemanager.order.repository.OrderedPicturesCountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderRepository photoOrderRepository;

    @Autowired
    DateFormatValidator dateFormatValidator;

    @Autowired
    OrderedPictureResponseMapper responseMapper;

    @PostMapping("searchOrderedPicturesForPeriod")
    public ResponseEntity<OrderedPicturesDetailsResponseDTO> searchOrderedPicturesForPeriod(@RequestBody @Valid OrderedPicturesDetailsRequestDTO request,
                                                                                            BindingResult bindingResult) {
        dateFormatValidator.validate(request, bindingResult);
        if(bindingResult.hasErrors()) {
            return new ResponseEntity(responseMapper.buildErrorResponse(bindingResult), HttpStatus.BAD_REQUEST);
        }

        List<OrderedPicturesCountDTO> results = getOrderedPicturesCount(request);
        return ResponseEntity.ok(responseMapper.buildSuccessfulResponse(results));
    }

    private List<OrderedPicturesCountDTO> getOrderedPicturesCount(OrderedPicturesDetailsRequestDTO requestData) {
        LocalDate startDate = LocalDate.parse(requestData.getStartDate(), DateFormatValidator.DATE_FORMAT);
        LocalDate endDate = LocalDate.parse(requestData.getEndDate(), DateFormatValidator.DATE_FORMAT);
        return photoOrderRepository.countOrderedPicturesForPeriod(startDate, endDate);
    }
}
