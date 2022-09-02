package com.example.propertymanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Lease {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    private double rent;

    @OneToOne(mappedBy = "lease")
    private Tenant tenant;

    public Lease(LocalDate startDate, LocalDate endDate, double rent) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.rent = rent;
    }

    @Override
    public String toString() {
        return "Lease{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", rent=" + rent +
                '}';
    }
}
