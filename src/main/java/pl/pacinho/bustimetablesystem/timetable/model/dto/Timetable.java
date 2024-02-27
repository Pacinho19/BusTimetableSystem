package pl.pacinho.bustimetablesystem.timetable.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Timetable {

    private BusRideDto ride;
    private List<LocalTime> arriveTimes;
    private List<BusStopTimeDto> stops;
}
