package com.pantkiewicz.demo.picturemanager.order.dto;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

@Component
public class DateFormatValidator {
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(DATE_PATTERN);

    private static final String INCORRECT_FORMAT_MESSAGE = "Incorrect date format, expected: " + DATE_PATTERN;
    private static final String INCORRECT_PERIOD_MESSAGE = "Incorrect period; startDate must be earlier than endDate";


    public void validate(OrderedPicturesDetailsRequestDTO data, BindingResult bindingResult) {
        if(Objects.nonNull(data.getStartDate()) && !isValidDate(data.getStartDate())) {
            bindingResult.addError(new ObjectError("startDate", INCORRECT_FORMAT_MESSAGE));
        }

        if(Objects.nonNull(data.getEndDate()) && !isValidDate(data.getEndDate())) {
            bindingResult.addError(new ObjectError("endDate", INCORRECT_FORMAT_MESSAGE));
        }

        if(!bindingResult.hasErrors() && !isCorrectPeriod(data.getStartDate(), data.getEndDate())) {
            bindingResult.addError(new ObjectError("startDate", INCORRECT_PERIOD_MESSAGE));
        }
    }

    private boolean isValidDate(String date) {
        try {
            LocalDate.parse(date, DATE_FORMAT);
            return true;
        } catch(DateTimeParseException e) {
            return false;
        }
    }

    private boolean isCorrectPeriod(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate, DATE_FORMAT);
        LocalDate end = LocalDate.parse(endDate, DATE_FORMAT);
        return start.isBefore(end);
    }
}
