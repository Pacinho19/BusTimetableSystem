package pl.pacinho.bustimetablesystem.timetable.model;

import pl.pacinho.bustimetablesystem.bus.model.entity.BusRouteStop;

public record FilterBusRouteDto(BusRouteStop initialBusStop, BusRouteStop finalBusStop) {
}
