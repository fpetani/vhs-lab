package com.example.vhs.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "vhs_id")
    private VHS vhs;

    @Min(value = 2, message = "You can't rent vhs for less than 2 days")
    @Max(value = 30, message = "You can't rent vhs for more than 30 days")
    private Integer rentalDuration;

    private LocalDate rentalDate;

    private LocalDate dueDate;

    private LocalDate returnDate;

    private Double lateFee;

    public Rental() {
    }
}
