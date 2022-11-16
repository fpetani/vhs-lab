package com.example.vhs.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    private LocalDate rentalDate;

    private LocalDate dueDate;

    private LocalDate returnDate;

    private Double lateFee;
}
