package com.example.vhs;

import com.example.vhs.entity.VHS;
import com.example.vhs.request.RentalForm;
import com.example.vhs.request.ReturnForm;

public class GenerateClass {

    public static RentalForm generateRentalForm(){
        RentalForm form = new RentalForm();
        form.setRentalDate("2022-01-01");
        form.setRentalDuration(14);
        form.setUserId(1L);
        form.setVhsId(1L);
        return form;
    }

    public static RentalForm generateBadRentalForm(){
        RentalForm form = new RentalForm();
        form.setRentalDate("2022-01-01");
        //Should be between 2 and 30
        form.setRentalDuration(35);
        form.setUserId(1L);
        form.setVhsId(1L);
        return form;
    }

    public static ReturnForm generateReturnForm(){
        ReturnForm form = new ReturnForm();
        form.setReturnDate("2022-02-01");
        return form;
    }

    public static VHS generateVHS(){
        VHS vhs = new VHS();

        vhs.setId(1L);
        vhs.setGenre("Drama");
        vhs.setRented(false);
        vhs.setTitle("Title");
        vhs.setBudget(5.2);
        vhs.setLength(90);
        vhs.setYear(2000);
        vhs.setFeePerDay(0.5);
        
        return vhs;
    }

    public static ReturnForm generateBadReturnForm(){
        ReturnForm form = new ReturnForm();
        //String should be 10 characters long
        form.setReturnDate("2022-021-01");
        return form;
    }
}
