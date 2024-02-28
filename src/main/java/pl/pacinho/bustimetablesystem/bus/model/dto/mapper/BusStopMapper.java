package pl.pacinho.bustimetablesystem.bus.model.dto.mapper;

import pl.pacinho.bustimetablesystem.bus.model.dto.BusStopDto;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusRouteStop;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusStop;

import java.util.List;
import java.util.stream.Collectors;

public class BusStopMapper {
    public static BusStopDto convert(BusStop busStop) {
        return new BusStopDto(
                busStop.getName(),
                busStop.getAddress()
        );
    }

    public static List<BusStopDto> convert(List<BusRouteStop> routeStops) {
        return routeStops.stream()
                .map(busRouteStop -> convert(busRouteStop.getBusStop()))
                .collect(Collectors.toList());
    }
}
