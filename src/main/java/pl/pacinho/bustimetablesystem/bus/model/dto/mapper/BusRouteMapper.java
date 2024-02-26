package pl.pacinho.bustimetablesystem.bus.model.dto.mapper;

import pl.pacinho.bustimetablesystem.bus.model.dto.BusRouteDto;
import pl.pacinho.bustimetablesystem.bus.model.dto.BusRouteStopDto;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusRoute;
import pl.pacinho.bustimetablesystem.bus.tools.BusStopTools;

import java.util.List;

public class BusRouteMapper {
    public static List<BusRouteDto> convert(List<BusRoute> busRoutes) {
        return busRoutes.stream()
                .map(BusRouteMapper::convert)
                .toList();
    }

    public static BusRouteDto convert(BusRoute busRoute) {
        return new BusRouteDto(
                BusRouteStopMapper.convert(BusStopTools.findFirstStopOnTheRoute(busRoute.getBusStops())),
                BusRouteStopMapper.convert(BusStopTools.findLastStopOnTheRoute(busRoute.getBusStops()))
        );
    }
}
