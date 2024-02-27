package pl.pacinho.bustimetablesystem.timetable.model.dto;

import pl.pacinho.bustimetablesystem.bus.model.dto.BusStopDto;

public record BusStopTimeDto(BusStopDto busStop, int time) {
}
