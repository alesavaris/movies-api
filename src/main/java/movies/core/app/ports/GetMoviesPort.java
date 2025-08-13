package movies.core.app.ports;

import movies.core.app.dtos.MovieDTO;
import org.springframework.stereotype.Component;

import java.util.List;

public interface GetMoviesPort {
    public List<MovieDTO> getMovies(Boolean justWinners);
}
