package pl.pacinho.bustimetablesystem.timetable.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pacinho.bustimetablesystem.bus.model.dto.mapper.BusMapper;
import pl.pacinho.bustimetablesystem.bus.model.dto.mapper.BusStopMapper;
import pl.pacinho.bustimetablesystem.bus.model.entity.Bus;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusRide;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusRoute;
import pl.pacinho.bustimetablesystem.bus.repository.BusRideRepository;
import pl.pacinho.bustimetablesystem.timetable.model.dto.BusRideDto;
import pl.pacinho.bustimetablesystem.timetable.model.dto.BusStopTimeDto;
import pl.pacinho.bustimetablesystem.timetable.model.dto.Timetable;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TimetableService {

    private final BusRideRepository busRideRepository;

    public Optional<Timetable> generate(int busNumber) {
        Map<BusRoute, List<BusRide>> busRides = busRideRepository.findAllWithFetchBusAndStops(busNumber)
                .stream()
                .collect(Collectors.groupingBy(BusRide::getBusRoute));

        if (busRides.isEmpty())
            return Optional.empty();

        return Optional.of(convertTimetable(busRides));
    }

    private Timetable convertTimetable(Map<BusRoute, List<BusRide>> busRoutes) {
        Bus bus = busRoutes.keySet().iterator().next().getBus();
        return Timetable.builder()
                .busDto(BusMapper.convert(bus))
                .rides(collectRides(busRoutes))
                .build();
    }

    private List<BusRideDto> collectRides(Map<BusRoute, List<BusRide>> busRoutes) {
        return busRoutes.entrySet()
                .stream()
                .map(entry -> convertToBusRide(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(BusRideDto::id))
                .collect(Collectors.toList());
    }

    private BusRideDto convertToBusRide(BusRoute busRoute, List<BusRide> busRides) {
        return BusRideDto.builder()
                .id(busRoute.getId())
                .distance(busRoute.getDistance())
                .summaryTime(busRoute.getSummaryTime())
                .arriveTimes(
                        busRides.stream()
                                .map(BusRide::getArriveTime)
                                .sorted()
                                .collect(Collectors.toList()))
                .stops(
                        busRoute.getBusStops()
                                .stream()
                                .map(busRouteStop -> new BusStopTimeDto(BusStopMapper.convert(busRouteStop.getBusStop()), busRouteStop.getMinutesFromStart()))
                                .sorted(Comparator.comparing(BusStopTimeDto::time))
                                .collect(Collectors.toList()))
                .build();
    }
}
