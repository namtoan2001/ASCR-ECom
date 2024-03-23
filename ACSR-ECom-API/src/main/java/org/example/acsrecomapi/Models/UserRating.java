package org.example.acsrecomapi.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class UserRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long UserRatingId;
    private int score;
    private String comment;
    private Date RatedDate;
    @ManyToOne
    @JoinColumn(name = "users", referencedColumnName = "userId")
    private Users users;
    @ManyToOne
    @JoinColumn(name = "rating", referencedColumnName = "ratingId")
    private Rating rating;
}
