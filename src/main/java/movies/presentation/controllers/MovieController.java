package movies.presentation.controllers;

import lombok.RequiredArgsConstructor;
import movies.core.app.dtos.AwardIntervalDTO;
import movies.core.app.dtos.MovieDTO;
import movies.core.app.usecases.GetMoviesUseCase;
import movies.core.app.usecases.WinnersUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final GetMoviesUseCase getMoviesUseCase;
    private final WinnersUseCase winnersUseCase;

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getMovies() {
        try {
            return ResponseEntity
                    .status(200)
                    .body(this.getMoviesUseCase.execute());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/winners")
    public ResponseEntity<AwardIntervalDTO> getWinners() {
        try {
            return ResponseEntity
                    .status(200)
                    .body(this.winnersUseCase.execute());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
