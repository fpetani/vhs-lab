package com.example.vhs.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class RentalForm {

    private Long userId;

    private Long vhsId;
    //I want date in yyyy-mm-dd string format
    @NotEmpty
    @Size(max = 10, min = 10)
    private String rentalDate;

    @Min(value = 2)
    @Max(value = 30)
    private Integer rentalDuration;
}
