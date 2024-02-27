package pl.pacinho.bustimetablesystem.bus.model.dto.mapper;

import pl.pacinho.bustimetablesystem.bus.model.dto.BusRouteDto;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusRide;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusRoute;
import pl.pacinho.bustimetablesystem.bus.tools.BusStopTools;

import java.time.LocalTime;
import java.util.List;

public class BusRouteMapper {
    public static List<BusRouteDto> convert(List<BusRide> busRides) {
        return busRides.stream()
                .map(br -> convert(br.getBusRoute(), br.getArriveTime()))
                .toList();
    }

    public static BusRouteDto convert(BusRoute busRoute, LocalTime arriveTime) {
        return new BusRouteDto(
                BusRouteStopMapper.convert(BusStopTools.findFirstStopOnTheRoute(busRoute.getBusStops()), arriveTime),
                BusRouteStopMapper.convert(BusStopTools.findLastStopOnTheRoute(busRoute.getBusStops()), arriveTime)
        );
    }
}
