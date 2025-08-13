package movies.core.app.dtos;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class AwardIntervalDTO {
    private List<WinnerDTO> min;
    private List<WinnerDTO> max;
}
