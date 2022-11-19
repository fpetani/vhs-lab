package com.example.vhs.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class RentalForm {

    private String username;

    private Long vhsId;
    @NotEmpty
    @Size(max = 10, min = 10, message = "This application supports dates in yyyy-mm-dd string format")
    private String rentalDate;

    @Min(value = 2, message = "You can not rent vhs for less than 2 days")
    @Max(value = 30, message = "You can not rent vhs for more than 30 days")
    private Integer rentalDuration;
}
