package pl.pacinho.bustimetablesystem.bus.model.dto.mapper;

import pl.pacinho.bustimetablesystem.bus.model.dto.BusDto;
import pl.pacinho.bustimetablesystem.bus.model.entity.Bus;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusRoute;

import java.util.List;
import java.util.Optional;

public class BusMapper {
    public static Optional<BusDto> convert(List<BusRoute> busRoutes) {
        if (busRoutes.isEmpty())
            return Optional.empty();

        Bus bus = busRoutes.get(0).getBus();

        return Optional.of(
                new BusDto(bus.getNumber(),
                        bus.getNumberOfSeats(),
                        bus.getNumberOfStandingSeats(),
                        BusRouteMapper.convert(busRoutes)
                        )
        );
    }
}
