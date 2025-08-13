package movies.core.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MovieDTO {
    private Integer year;
    private String title;
    private String studios;
    private String producers;
    private Boolean winner;
}
