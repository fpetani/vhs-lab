package com.example.vhs.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RentalForm {
    @NotBlank
    private Long userId;

    @NotBlank
    private Long vhsId;

    @NotBlank
    @Size(max = 10)
    private String rentalDate;

    @NotBlank
    @Min(value = 2)
    @Max(value = 30)
    private Integer rentalDuration;
}
