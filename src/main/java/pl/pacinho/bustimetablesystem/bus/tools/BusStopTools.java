package pl.pacinho.bustimetablesystem.bus.tools;

import pl.pacinho.bustimetablesystem.bus.model.entity.BusRouteStop;

import java.util.Comparator;
import java.util.Set;

public class BusStopTools {
    public static BusRouteStop findFirstStopOnTheRoute(Set<BusRouteStop> busStops) {
        return busStops.stream()
                .min(Comparator.comparing(BusRouteStop::getNumber))
                .orElse(null);
    }

    public static BusRouteStop findLastStopOnTheRoute(Set<BusRouteStop> busStops) {
        return busStops.stream()
                .max(Comparator.comparing(BusRouteStop::getNumber))
                .orElse(null);
    }
}
