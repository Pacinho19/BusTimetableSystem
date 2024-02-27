package pl.pacinho.bustimetablesystem.bus.tools;

import pl.pacinho.bustimetablesystem.bus.model.entity.BusRouteStop;

import java.util.List;

public class BusRouteStopPreviousStopSetter {
    public static void setPreviousStop(BusRouteStop busRouteStop, List<BusRouteStop> stops) {
        busRouteStop.setPreviousStop(getPreviousStop(busRouteStop.getNumber(), stops));
    }

    private static BusRouteStop getPreviousStop(int number, List<BusRouteStop> stops) {
        return stops.stream()
                .filter(busRouteStop -> busRouteStop.getNumber() - 1 == number)
                .findFirst()
                .orElse(null);
    }
}
