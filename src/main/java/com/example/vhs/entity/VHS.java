package com.example.vhs.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity
@DynamicInsert
@Getter
@Setter
public class VHS {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private Double budget;

    @Column(nullable = false, length = 150)
    private String genre;

    @Column(nullable = false, length = 150)
    private String title;

    @Min(value = 0, message = "Length cannot be negative")
    @Max(value = 1000, message = "length of an VHS cannot be more than 1000 minutes")
    @Column(nullable = false)
    private Integer length;

    @Min(value = 1900, message = "Year cannot be less than 1900")
    @Max(value = 2023, message = "Year cannot be greater than 2023")
    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean isRented;

    public VHS() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VHS)) return false;
        VHS vhs = (VHS) o;
        return Id.equals(vhs.Id) && genre.equals(vhs.genre) && title.equals(vhs.title) && year.equals(vhs.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, genre, title, year);
    }
}
