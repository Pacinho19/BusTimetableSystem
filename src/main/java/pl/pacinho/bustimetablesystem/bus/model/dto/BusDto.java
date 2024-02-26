package pl.pacinho.bustimetablesystem.bus.model.dto;

import pl.pacinho.bustimetablesystem.bus.model.entity.Bus;

import java.util.List;

public record BusDto(int number,
                     int numberOfSeats,
                     int numberOfStandingSeats,
                     List<BusRouteDto> routes) {
}
