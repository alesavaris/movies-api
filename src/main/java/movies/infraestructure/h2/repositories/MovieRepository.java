package movies.infraestructure.h2.repositories;

import movies.infraestructure.h2.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByWinner(boolean winner);
}
