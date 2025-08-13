package movies.infraestructure.h2.mappers;

import movies.core.app.dtos.MovieDTO;
import movies.infraestructure.h2.models.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public static MovieDTO toDto(Movie movie) {
        return new MovieDTO(
                movie.getAwardYear(),
                movie.getTitle(),
                movie.getStudios(),
                movie.getProducers(),
                movie.getWinner()
        );
    }
}
