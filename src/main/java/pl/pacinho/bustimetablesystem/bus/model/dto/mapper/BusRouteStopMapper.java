package pl.pacinho.bustimetablesystem.bus.model.dto.mapper;

import pl.pacinho.bustimetablesystem.bus.model.dto.BusRouteStopDto;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusRouteStop;

public class BusRouteStopMapper {
    public static BusRouteStopDto convert(BusRouteStop busRouteStop) {
        return new BusRouteStopDto(
                BusStopMapper.convert(busRouteStop.getBusStop()),
                busRouteStop.getArrivalTime(),
                busRouteStop.getDepartureTime()
        );
    }
}
