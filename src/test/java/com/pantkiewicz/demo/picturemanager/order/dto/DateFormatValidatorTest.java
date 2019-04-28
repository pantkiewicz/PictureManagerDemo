package com.pantkiewicz.demo.picturemanager.order.dto;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DateFormatValidatorTest {

    @Autowired
    DateFormatValidator dateValidator;

    @Test
    public void whenStartDateBeforeEndDate_thenOK() {
        OrderedPicturesDetailsRequestDTO request = new OrderedPicturesDetailsRequestDTO();
        request.setStartDate("2019-05-01");
        request.setEndDate("2019-05-02");
        BindingResult bindingResult = new BeanPropertyBindingResult(request, "request");

        dateValidator.validate(request, bindingResult);

        Assert.assertTrue(bindingResult.getAllErrors().isEmpty());
    }

    @Test
    public void whenStartDateAfterEndDate_thenFAIL() {
        OrderedPicturesDetailsRequestDTO request = new OrderedPicturesDetailsRequestDTO();
        request.setStartDate("2019-05-02");
        request.setEndDate("2019-05-01");
        BindingResult bindingResult = new BeanPropertyBindingResult(request, "request");

        dateValidator.validate(request, bindingResult);

        Assert.assertFalse(bindingResult.getAllErrors().isEmpty());
    }

    @Test
    public void whenStartDateEqualsEndDate_thenFAIL() {
        OrderedPicturesDetailsRequestDTO request = new OrderedPicturesDetailsRequestDTO();
        request.setStartDate("2019-05-02");
        request.setEndDate("2019-05-02");
        BindingResult bindingResult = new BeanPropertyBindingResult(request, "request");

        dateValidator.validate(request, bindingResult);

        Assert.assertFalse(bindingResult.getAllErrors().isEmpty());
    }

    @Test
    public void whenStartDateFormatIncorrect_thenFAIL() {
        OrderedPicturesDetailsRequestDTO request = new OrderedPicturesDetailsRequestDTO();
        request.setStartDate("01-05-2019");
        request.setEndDate("2019-05-02");
        BindingResult bindingResult = new BeanPropertyBindingResult(request, "request");

        dateValidator.validate(request, bindingResult);

        Assert.assertFalse(bindingResult.getAllErrors().isEmpty());
    }

    @Test
    public void whenEndDateFormatIncorrect_thenFAIL() {
        OrderedPicturesDetailsRequestDTO request = new OrderedPicturesDetailsRequestDTO();
        request.setStartDate("2019-05-01");
        request.setEndDate("02-05-2019");
        BindingResult bindingResult = new BeanPropertyBindingResult(request, "request");

        dateValidator.validate(request, bindingResult);

        Assert.assertFalse(bindingResult.getAllErrors().isEmpty());
    }
}
