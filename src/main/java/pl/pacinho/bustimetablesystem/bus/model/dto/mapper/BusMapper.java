package pl.pacinho.bustimetablesystem.bus.model.dto.mapper;

import pl.pacinho.bustimetablesystem.bus.model.dto.BusDto;
import pl.pacinho.bustimetablesystem.bus.model.entity.Bus;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusRide;

import java.util.List;
import java.util.Optional;

public class BusMapper {
    public static Optional<BusDto> convert(List<BusRide> busRides) {
        if (busRides.isEmpty())
            return Optional.empty();

        Bus bus = busRides.get(0).getBus();

        return Optional.of(
                new BusDto(bus.getNumber(),
                        bus.getNumberOfSeats(),
                        bus.getNumberOfStandingSeats(),
                        BusRouteMapper.convert(busRides)
                )
        );
    }
}
