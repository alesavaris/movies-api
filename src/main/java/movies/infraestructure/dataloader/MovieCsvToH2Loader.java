package movies.infraestructure.dataloader;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import movies.infraestructure.h2.repositories.MovieRepository;
import movies.infraestructure.h2.models.Movie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class MovieCsvToH2Loader implements CommandLineRunner {

    private final MovieRepository movieRepository;
    private static final Logger logger = Logger.getLogger(MovieCsvToH2Loader.class.getName());

    public MovieCsvToH2Loader(MovieRepository repository){
        this.movieRepository = repository;
    }

    @Override
    public void run(String... args) throws Exception {

        char separator = ';';
        ClassPathResource resource = new ClassPathResource("movies.csv");
        if (!resource.exists()){
            logger.log(Level.SEVERE, "File does not exist: 'movies.csv'");
            System.exit(1);
        }

        try ( FileReader fileReader = new FileReader(resource.getFile());
             CSVReader csvReader = new CSVReaderBuilder(fileReader)
                     .withSkipLines(1)
                     .withCSVParser(new CSVParserBuilder()
                             .withSeparator(separator)
                             .build())
                     .build()
        ) {
            List<String[]> lines = csvReader.readAll();
            for (String[] line: lines){
                Movie movie = getMovie(line);
                this.movieRepository.save(movie);
            }
            logger.info("Data loaded successfully...");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error reading data file...", e);
            System.exit(1);
        }
    }

    private static Movie getMovie(String[] line) {

        Integer year = Integer.parseInt(line[0]);
        String title = line[1].trim();
        String studios = line[2].trim();
        String producers = line[3].trim();

        String winner = line.length == 5 ? line[4].trim() : "";
        Boolean isWinner = winner.equals("yes");

        Movie movie = new Movie();
        movie.setAwardYear(year);
        movie.setTitle(title);
        movie.setStudios(studios);
        movie.setProducers(producers);
        movie.setWinner(isWinner);
        return movie;
    }
}
