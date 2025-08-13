package movies.core.app.usecases;

import movies.core.app.dtos.MovieDTO;
import movies.core.app.ports.GetMoviesPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetMoviesUseCase {

    private final GetMoviesPort moviesPort;

    public GetMoviesUseCase(GetMoviesPort moviesPort) {
        this.moviesPort = moviesPort;
    }

    public List<MovieDTO> execute(){
        return this.moviesPort.getMovies(false);
    }

}
