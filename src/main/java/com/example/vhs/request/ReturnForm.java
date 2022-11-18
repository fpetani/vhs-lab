package com.example.vhs.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ReturnForm {
    @NotBlank
    @Size(max = 10, min = 10, message = "This application supports dates in yyyy-mm-dd string format")
    private String returnDate;

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}