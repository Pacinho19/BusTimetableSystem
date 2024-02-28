package pl.pacinho.bustimetablesystem.timetable.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.pacinho.bustimetablesystem.bus.model.dto.BusDto;
import pl.pacinho.bustimetablesystem.bus.model.dto.BusStopDto;

import java.time.LocalTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RideSearchResultDto {

    private BusDto bus;
    private int totalTime;
    private List<LocalTime> times;
    private List<BusStopDto> stops;
}
