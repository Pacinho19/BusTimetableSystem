package pl.pacinho.bustimetablesystem.bus.model.dto;

import java.time.LocalTime;

public record BusRouteStopDto(BusStopDto busStop, LocalTime arriveTime) {
}
