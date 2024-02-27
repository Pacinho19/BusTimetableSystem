package pl.pacinho.bustimetablesystem.bus.model.dto;

import java.util.List;

public record BusDto(int number,
                     int numberOfSeats,
                     int numberOfStandingSeats,
                     List<BusRouteDto> rides) {
}
