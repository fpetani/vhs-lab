package com.example.vhs.request;

import javax.validation.constraints.NotBlank;

public class ReturnForm {
    @NotBlank
    private String returnDate;

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
