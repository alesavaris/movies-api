package movies.infraestructure.h2.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer awardYear;
    private String title;
    private String studios;
    private String producers;
    private Boolean winner;

    public Movie() {
    }
}
