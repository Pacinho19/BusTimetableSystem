package pl.pacinho.bustimetablesystem.timetable.tools;

import lombok.NonNull;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusRouteStop;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusStop;
import pl.pacinho.bustimetablesystem.timetable.model.FilterBusRouteDto;

import java.util.*;
import java.util.stream.Collectors;

public class BusRideFilter {

    public static List<FilterBusRouteDto> filterBusRoute(Set<BusRouteStop> busRouteStops, @NonNull String from, @NonNull String to) {
        List<BusRouteStop> initialBusStops = getBusStop(busRouteStops, from);
        if (initialBusStops.isEmpty())
            return Collections.emptyList();

        List<BusRouteStop> finalBusStops = getBusStop(busRouteStops, to);
        if (finalBusStops.isEmpty())
            return Collections.emptyList();

        List<FilterBusRouteDto> filteredRoute = new ArrayList<>();
        for (BusRouteStop initialBusStop : initialBusStops) {
            for (BusRouteStop finalBusStop : finalBusStops) {
                if (initialBusStop.getNumber() < finalBusStop.getNumber())
                    filteredRoute.add(new FilterBusRouteDto(initialBusStop, finalBusStop));
            }
        }
        return filteredRoute;
    }

    private static List<BusRouteStop> getBusStop(Set<BusRouteStop> busRouteStops, String from) {
        return busRouteStops.stream()
                .filter(brs -> checkBusStop(brs.getBusStop(), from))
                .collect(Collectors.toList());
    }

    private static boolean checkBusStop(BusStop busStop, String partOfBusStop) {
        return checkLowerCaseFieldContains(busStop.getAddress(), partOfBusStop)
               || checkLowerCaseFieldContains(busStop.getName(), partOfBusStop);
    }

    private static boolean checkLowerCaseFieldContains(String field, String text) {
        return field.toLowerCase()
                .contains(text.toLowerCase());
    }

    public static List<BusRouteStop> getRouteBetweenStops(Set<BusRouteStop> busStops, int initialBusStopNumber,  int finalBusStopNumber) {
        return busStops.stream()
                .filter(busRouteStop -> busRouteStop.getNumber() >= initialBusStopNumber
                                        && busRouteStop.getNumber() <= finalBusStopNumber)
                .sorted(Comparator.comparing(BusRouteStop::getNumber))
                .collect(Collectors.toList());
    }
}
