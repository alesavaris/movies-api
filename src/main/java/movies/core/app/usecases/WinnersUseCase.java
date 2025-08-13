package movies.core.app.usecases;

import movies.core.app.dtos.AwardIntervalDTO;
import movies.core.app.dtos.MovieDTO;
import movies.core.app.dtos.WinnerDTO;
import movies.core.app.ports.GetMoviesPort;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class WinnersUseCase {

    static class AwardInterval {
        String producer;
        int previousYear;
        int currentYear;
        int interval;

        public AwardInterval(String producer, int previousYear, int currentYear) {
            this.producer = producer;
            this.previousYear = previousYear;
            this.currentYear = currentYear;
            this.interval = currentYear - previousYear;
        }
    }

    private final GetMoviesPort moviesPort;

    public WinnersUseCase(GetMoviesPort port) {
        this.moviesPort = port;
    }

    public AwardIntervalDTO execute(){
        List<MovieDTO> winners = this.moviesPort.getMovies(true);
        Map<String, List<Integer>> awardsByProducers = new HashMap<>();
        for (MovieDTO movie : winners) {
            for (String producer: movie.getProducers().split(",")){
                awardsByProducers.computeIfAbsent(producer.trim(), k -> new ArrayList<>()).add(movie.getYear());
            }
        }

        List<AwardInterval> longerIntervals = new ArrayList<>();
        List<AwardInterval> shorterIntervals = new ArrayList<>();

        AwardInterval longerInterval = null;
        AwardInterval shorterInterval = null;

        for (Map.Entry<String, List<Integer>> entry : awardsByProducers.entrySet()) {
            String producer = entry.getKey();
            List<Integer> awards = entry.getValue();
            Collections.sort(awards);

            for(int  i = 1; i < awards.size(); i++){
                AwardInterval current = new AwardInterval(producer, awards.get(i - 1), awards.get(i));
                if (longerInterval == null || current.interval > longerInterval.interval) {
                    longerInterval = current;
                    longerIntervals.clear();
                    longerIntervals.add(current);
                } else if (current.interval == longerInterval.interval) {
                    longerIntervals.add(current);
                }

                if (shorterInterval == null || current.interval < shorterInterval.interval) {
                    shorterInterval = current;
                    shorterIntervals.clear();
                    shorterIntervals.add(current);
                } else if (current.interval == shorterInterval.interval) {
                    shorterIntervals.add(current);
                }
            }
        }
        return getResume(longerIntervals, shorterIntervals);
    }

    private AwardIntervalDTO getResume(List<AwardInterval> longerList, List<AwardInterval> shorterList) {
        List<WinnerDTO> min = new ArrayList<>();
        List<WinnerDTO> max = new ArrayList<>();

        for (AwardInterval longer: longerList){
            max.add(new WinnerDTO(
                    longer.producer,
                    longer.interval,
                    longer.previousYear,
                    longer.currentYear
            ));
        }

        for (AwardInterval shorter: shorterList){
            min.add(new WinnerDTO(
                    shorter.producer,
                    shorter.interval,
                    shorter.previousYear,
                    shorter.currentYear
            ));
        }

        return new AwardIntervalDTO(min, max);
    }

}
