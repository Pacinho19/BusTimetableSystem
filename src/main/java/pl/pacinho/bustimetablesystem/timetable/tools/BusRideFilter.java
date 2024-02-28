package pl.pacinho.bustimetablesystem.timetable.tools;


import pl.pacinho.bustimetablesystem.bus.model.entity.BusRouteStop;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusStop;
import pl.pacinho.bustimetablesystem.timetable.model.FilterBusRouteDto;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class BusRideFilter {

    public static FilterBusRouteDto filterBusRoute(Set<BusRouteStop> busRouteStops, String from, String to) {
        Optional<BusRouteStop> initialBusStopOpt = getBusStopWithMinNumber(busRouteStops, from);
        if (initialBusStopOpt.isEmpty())
            return null;

        Optional<BusRouteStop> finalBusStopOpt = getBusStopWithMinNumber(busRouteStops, to);

        if (finalBusStopOpt.isEmpty())
            return null;

        BusRouteStop initialBusStop = initialBusStopOpt.get();
        BusRouteStop finalBusStop = finalBusStopOpt.get();

        return initialBusStop.getNumber() < finalBusStop.getNumber()
                ? new FilterBusRouteDto(initialBusStop, finalBusStop)
                : null;
    }

    private static Optional<BusRouteStop> getBusStopWithMinNumber(Set<BusRouteStop> busRouteStops, String from) {
        return busRouteStops.stream()
                .filter(brs -> checkBusStop(brs.getBusStop(), from))
                .min(Comparator.comparing(BusRouteStop::getNumber));
    }

    private static boolean checkBusStop(BusStop busStop, String partOfBusStop) {
        return checkLowerCaseFieldContains(busStop.getAddress(), partOfBusStop)
               || checkLowerCaseFieldContains(busStop.getName(), partOfBusStop);
    }

    private static boolean checkLowerCaseFieldContains(String field, String text) {
        return field.toLowerCase()
                .contains(text.toLowerCase());
    }

    public static List<BusRouteStop> getRouteBetweenStops(Set<BusRouteStop> busStops, BusRouteStop initialBusStop, BusRouteStop finalBusStop) {
        return busStops.stream()
                .filter(busRouteStop -> busRouteStop.getNumber() >= initialBusStop.getNumber()
                                        && busRouteStop.getNumber() <= finalBusStop.getNumber())
                .sorted(Comparator.comparing(BusRouteStop::getNumber))
                .collect(Collectors.toList());
    }
}
