package pl.pacinho.bustimetablesystem.timetable.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pacinho.bustimetablesystem.bus.model.dto.BusStopDto;
import pl.pacinho.bustimetablesystem.bus.model.dto.mapper.BusMapper;
import pl.pacinho.bustimetablesystem.bus.model.dto.mapper.BusStopMapper;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusRide;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusRoute;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusRouteStop;
import pl.pacinho.bustimetablesystem.bus.repository.BusRideRepository;
import pl.pacinho.bustimetablesystem.exception.InvalidBusStopNameException;
import pl.pacinho.bustimetablesystem.timetable.model.FilterBusRouteDto;
import pl.pacinho.bustimetablesystem.timetable.model.RideSearchResultDto;
import pl.pacinho.bustimetablesystem.timetable.tools.BusRideFilter;
import pl.pacinho.bustimetablesystem.validator.StringEmptyFieldValidator;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RideSearchService {

    private final BusRideRepository busRideRepository;

    public List<RideSearchResultDto> search(String from, String to) {
        if (StringEmptyFieldValidator.isNonNullAndNotEmpty(from) || StringEmptyFieldValidator.isNonNullAndNotEmpty(to))
            throw new InvalidBusStopNameException();

        List<BusRide> busRides = busRideRepository.findAllWithFetchBusAndStops();
        return busRides.stream()
                .collect(Collectors.groupingBy(BusRide::getBusRoute))
                .entrySet()
                .stream()
                .map(entry -> mapToRideSearchResultDto(entry.getKey(), entry.getValue(), from, to))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private RideSearchResultDto mapToRideSearchResultDto(BusRoute busRoute, List<BusRide> busRides, String from, String to) {
        FilterBusRouteDto filterBusRouteDto = BusRideFilter.filterBusRoute(busRoute.getBusStops(), from, to);
        if (filterBusRouteDto == null)
            return null;

        List<BusRouteStop> routeBetweenStops = BusRideFilter.getRouteBetweenStops(busRoute.getBusStops(),
                filterBusRouteDto.initialBusStop(),
                filterBusRouteDto.finalBusStop());


        return RideSearchResultDto.builder()
                .bus(BusMapper.convert(busRoute.getBus()))
                .totalTime(filterBusRouteDto.finalBusStop().getMinutesFromStart() - filterBusRouteDto.initialBusStop().getMinutesFromStart())
                .stops(BusStopMapper.convert(routeBetweenStops))
                .times(getBusArrivalTimeToInitialBusStop(busRides, filterBusRouteDto.initialBusStop()))
                .build();
    }

    private List<LocalTime> getBusArrivalTimeToInitialBusStop(List<BusRide> busRides, BusRouteStop busRouteStop) {
        return busRides.stream()
                .map(busRide -> busRide.getArriveTime().plusMinutes(busRouteStop.getMinutesFromStart()))
                .collect(Collectors.toList());
    }


}
