package pl.pacinho.bustimetablesystem.bus.model.dto.mapper;

import pl.pacinho.bustimetablesystem.bus.model.dto.BusRouteStopDto;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusRouteStop;

import java.time.LocalTime;

public class BusRouteStopMapper {
    public static BusRouteStopDto convert(BusRouteStop busRouteStop, LocalTime arriveTime) {
        return new BusRouteStopDto(
                BusStopMapper.convert(busRouteStop.getBusStop()),
                arriveTime.plusMinutes(busRouteStop.getMinutesFromStart())
        );
    }
}
