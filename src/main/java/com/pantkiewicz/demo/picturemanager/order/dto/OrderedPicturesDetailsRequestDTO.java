package com.pantkiewicz.demo.picturemanager.order.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class OrderedPicturesDetailsRequestDTO {

    @NotNull
    private String startDate;

    @NotNull
    private String endDate;
}
