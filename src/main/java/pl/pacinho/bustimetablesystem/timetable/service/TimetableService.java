package pl.pacinho.bustimetablesystem.timetable.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pacinho.bustimetablesystem.bus.model.dto.mapper.BusStopMapper;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusRide;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusRoute;
import pl.pacinho.bustimetablesystem.bus.repository.BusRideRepository;
import pl.pacinho.bustimetablesystem.timetable.model.dto.BusRideDto;
import pl.pacinho.bustimetablesystem.timetable.model.dto.BusStopTimeDto;
import pl.pacinho.bustimetablesystem.timetable.model.dto.Timetable;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TimetableService {

    private final BusRideRepository busRideRepository;

    public List<Timetable> generate(int busId) {
        Map<BusRoute, List<BusRide>> busRides = busRideRepository.findAllWithFetchBusAndStops(busId)
                .stream()
                .collect(Collectors.groupingBy(BusRide::getBusRoute));

        return busRides.entrySet()
                .stream()
                .map(this::convertFromBusRides)
                .collect(Collectors.toList());
    }

    private Timetable convertFromBusRides(Map.Entry<BusRoute, List<BusRide>> busRoutes) {
        return Timetable.builder()
                .ride(new BusRideDto(
                        busRoutes.getKey().getDistance(),
                        busRoutes.getKey().getSummaryTime()))
                .arriveTimes(busRoutes.getValue()
                        .stream()
                        .map(BusRide::getArriveTime)
                        .sorted()
                        .collect(Collectors.toList()))
                .stops(busRoutes.getKey()
                        .getBusStops()
                        .stream()
                        .map(busRouteStop -> new BusStopTimeDto(BusStopMapper.convert(busRouteStop.getBusStop()), busRouteStop.getMinutesFromStart()))
                        .sorted(Comparator.comparing(BusStopTimeDto::time))
                        .collect(Collectors.toList()))
                .build();
    }
}
