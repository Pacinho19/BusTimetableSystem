package pl.pacinho.bustimetablesystem.initializr;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.pacinho.bustimetablesystem.bus.model.entity.*;
import pl.pacinho.bustimetablesystem.bus.repository.BusRepository;
import pl.pacinho.bustimetablesystem.bus.repository.BusRideRepository;
import pl.pacinho.bustimetablesystem.bus.repository.BusRouteRepository;
import pl.pacinho.bustimetablesystem.bus.repository.BusStopRepository;
import pl.pacinho.bustimetablesystem.bus.tools.BusRouteStopPreviousStopSetter;

import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class BusRouteDataInitializer {

    private final BusRideRepository busRideRepository;
    private final BusRouteRepository busRouteRepository;
    private final BusStopRepository busStopRepository;
    private final BusRepository busRepository;

    @EventListener(ApplicationReadyEvent.class)
    void startApp() {
        Bus bus = busRepository.findByNumber(2)
                .orElse(new Bus(2, 50, 10));

        BusRoute busRoute = new BusRoute(bus, 15, 23);

        List<BusRouteStop> busRouteStops = List.of(
                new BusRouteStop(1, getBusStopOrCreate("Lubiatowo 01", "Lubiatowo ul. Maślana 6"), busRoute, 0),
                new BusRouteStop(2, getBusStopOrCreate("Dzierżęcino 01", "Dzierżęcino ul. Ruda 12"), busRoute, 5),
                new BusRouteStop(3, getBusStopOrCreate("Lubiatowska 01", "Koszalin ul. Lubiatowska 1"), busRoute, 7),
                new BusRouteStop(4, getBusStopOrCreate("Sianowska 01", "Koszalin ul. Sianowska 8"), busRoute, 10),
                new BusRouteStop(5, getBusStopOrCreate("Hospicjum  02", "Koszalin ul. Hospicyjna 17"), busRoute, 12),
                new BusRouteStop(6, getBusStopOrCreate("Chełmowo 01", "Koszalin ul. Chełmska 3"), busRoute, 14),
                new BusRouteStop(7, getBusStopOrCreate("Św Wojciecha 01", "Koszalin ul. Św Wojciech 31"), busRoute, 17),
                new BusRouteStop(8, getBusStopOrCreate("Zielona 01", "Koszalin ul. Zielona 12"), busRoute, 18),
                new BusRouteStop(9, getBusStopOrCreate("Moniuszki 01", "Koszalin ul. Moniuszki 13"), busRoute, 20),
                new BusRouteStop(10, getBusStopOrCreate("Miła 01", "Koszalin ul. Miła 1"), busRoute, 23)
        );

        busRouteStops.forEach(busRouteStop -> {
            BusRouteStopPreviousStopSetter.setPreviousStop(busRouteStop, busRouteStops);
            busRoute.addBusStop(busRouteStop);
        });
        busRouteRepository.save(busRoute);

        getRideArriveTimes()
                .forEach(time -> {
                    BusRide busRide = new BusRide(busRoute, time);
                    busRideRepository.save(busRide);
                });
    }

    private static List<LocalTime> getRideArriveTimes() {
        return List.of(LocalTime.of(4, 55), LocalTime.of(5, 55), LocalTime.of(6, 53), LocalTime.of(7, 23), LocalTime.of(8, 10),
                LocalTime.of(8, 55), LocalTime.of(9, 55), LocalTime.of(10, 55), LocalTime.of(11, 55), LocalTime.of(12, 55),
                LocalTime.of(14, 8), LocalTime.of(14, 52), LocalTime.of(15, 53), LocalTime.of(16, 56), LocalTime.of(17, 59),
                LocalTime.of(18, 59), LocalTime.of(19, 54), LocalTime.of(20, 55)
        );
    }

    private BusStop getBusStopOrCreate(String name, String address) {
        return busStopRepository.findByName(name)
                .orElse(new BusStop(name, address));
    }
}
