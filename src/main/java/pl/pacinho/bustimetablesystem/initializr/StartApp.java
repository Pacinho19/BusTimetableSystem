package pl.pacinho.bustimetablesystem.initializr;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.pacinho.bustimetablesystem.bus.model.entity.Bus;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusRoute;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusRouteStop;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusStop;
import pl.pacinho.bustimetablesystem.bus.repository.BusRouteRepository;

import java.time.LocalTime;
import java.util.Set;

@RequiredArgsConstructor
//@Component
public class StartApp {

    private final BusRouteRepository busRouteRepository;

    @EventListener(ApplicationReadyEvent.class)
    void startApp() {
        Bus bus = new Bus(99, 99, 99);
        BusRoute busRoute = new BusRoute();
        Set<BusRouteStop> busRouteStops = Set.of(
                new BusRouteStop(1, new BusStop("test", "test"), busRoute, LocalTime.of(7, 0), LocalTime.of(7, 1)),
                new BusRouteStop(2, new BusStop("test2", "test2"), busRoute, LocalTime.of(8, 0), LocalTime.of(8, 1)),
                new BusRouteStop(3, new BusStop("test3", "test3"), busRoute, LocalTime.of(9, 0), LocalTime.of(9, 1))
        );
        busRoute.setBus(bus);
        busRoute.setBusStops(busRouteStops);

        busRouteRepository.save(busRoute);
    }
}
