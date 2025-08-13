package movies.infraestructure.h2.adapters;

import movies.core.app.dtos.MovieDTO;
import movies.core.app.ports.GetMoviesPort;
import movies.infraestructure.h2.mappers.MovieMapper;
import movies.infraestructure.h2.models.Movie;
import movies.infraestructure.h2.repositories.MovieRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class H2MovieAdapter implements GetMoviesPort {

    private final MovieRepository repository;

    public H2MovieAdapter(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<MovieDTO> getMovies(Boolean justWinners) {
        List<Movie> movies = null;
        if (justWinners){
            movies = this.repository.findByWinner(true);
        } else {
            movies = this.repository.findAll();
        }
        return movies.stream()
                .map(MovieMapper::toDto)
                .collect(Collectors.toList());
    }
}
