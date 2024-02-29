package pl.pacinho.bustimetablesystem.timetable.model.dto;

import lombok.Builder;

import java.time.LocalTime;
import java.util.List;

@Builder
public record BusRideDto(int id, int distance, int summaryTime, List<LocalTime> arriveTimes,
                         List<BusStopTimeDto> stops) {
}
