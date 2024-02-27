package pl.pacinho.bustimetablesystem.initializr;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusStop;
import pl.pacinho.bustimetablesystem.bus.repository.BusRepository;
import pl.pacinho.bustimetablesystem.bus.repository.BusRouteRepository;
import pl.pacinho.bustimetablesystem.bus.repository.BusStopRepository;

@RequiredArgsConstructor
//@Component
public class BusRouteDataInitializer {

    private final BusRouteRepository busRouteRepository;
    private final BusRepository busRepository;
    private final BusStopRepository busStopRepository;

    @EventListener(ApplicationReadyEvent.class)
    void startApp() {
//        Bus bus = busRepository.findByNumber(2)
//                .orElse(new Bus(2, 50, 10));
//
//        BusRoute busRoute = new BusRoute();
//
//        List<BusRouteStop> busRouteStops = List.of(
//                new BusRouteStop(1, getBusStopOrCreate("Lubiatowo 01", "Lubiatowo ul. Maślana 6"), busRoute, null, 0)
//        );
//        busRouteStops.forEach(busRouteStop -> BusRouteStopPreviousStopSetter.setPreviousStop(busRouteStop, busRouteStops));
//
//        busRoute.setBus(bus);
//        busRoute.setBusStops(new HashSet<>(busRouteStops));
//
//        busRouteRepository.save(busRoute);
    }

    private BusStop getBusStopOrCreate(String name, String address) {
        return busStopRepository.findByName(name)
                .orElse(new BusStop(name, address));
    }
}
