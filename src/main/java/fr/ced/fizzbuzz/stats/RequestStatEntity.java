package fr.ced.fizzbuzz.stats;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="REQUEST_STAT")
@Data
public class RequestStatEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(name = "request")
    private String request;
    @Column(name = "hit_number")
    private int hitNumber = 0;
}
