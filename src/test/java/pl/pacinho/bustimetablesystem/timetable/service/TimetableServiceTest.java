package pl.pacinho.bustimetablesystem.timetable.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusRide;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusRoute;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusRouteStop;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusStop;
import pl.pacinho.bustimetablesystem.bus.repository.BusRideRepository;
import pl.pacinho.bustimetablesystem.timetable.model.dto.Timetable;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TimetableServiceTest {

    @InjectMocks
    private TimetableService timetableService;

    @Mock
    private BusRideRepository busRideRepository;

    @Test
    void emptyListShouldBeGeneratedWhenRouteForGivenBusIdNotExists() {
        //given
        given(busRideRepository.findAllWithFetchBusAndStops(anyInt())).willReturn(Collections.emptyList());

        //when
        List<Timetable> timetables = timetableService.generate(1);

        //then
        assertThat(timetables, hasSize(0));
        assertThat(timetables, empty());
    }

    @Test
    void timetableListWithOneElementShouldBeGeneratedWhenExistOneRouteForGivenBusId() {
        //given
        BusRoute busRoute = getBusRouteWithThreeStops();
        BusRide busRide = new BusRide(null, busRoute, LocalTime.now());
        List<BusRide> busRides = List.of(busRide);

        given(busRideRepository.findAllWithFetchBusAndStops(anyInt())).willReturn(busRides);

        //when
        List<Timetable> timetables = timetableService.generate(1);

        //then
        assertThat(timetables, not(empty()));
        assertThat(timetables, hasSize(1));
        assertThat(timetables.get(0).getArriveTimes(), hasSize(1));
        assertThat(timetables.get(0).getStops(), hasSize(3));
    }

    @Test
    void timetableListWithOneElementAndEmptyStopListShouldBeGeneratedWhenExistOneRouteWithoutStopsForGivenBusId() {
        //given
        BusRoute busRoute = getBusRouteWithThreeStops();
        busRoute.getBusStops().clear();
        BusRide busRide = new BusRide(null, busRoute, LocalTime.now());
        List<BusRide> busRides = List.of(busRide);

        given(busRideRepository.findAllWithFetchBusAndStops(anyInt())).willReturn(busRides);

        //when
        List<Timetable> timetables = timetableService.generate(1);

        //then
        assertThat(timetables, not(empty()));
        assertThat(timetables, hasSize(1));
        assertThat(timetables.get(0).getStops(), empty());
    }

    private BusRoute getBusRouteWithThreeStops() {
        BusRoute busRoute = new BusRoute(100, 100);
        busRoute.addBusStop(new BusRouteStop(1, new BusStop("Test", "Test"), busRoute, 0));
        busRoute.addBusStop(new BusRouteStop(2, new BusStop("Test2", "Tes2"), busRoute, 5));
        busRoute.addBusStop(new BusRouteStop(3, new BusStop("Test3", "Tes3"), busRoute, 10));
        return busRoute;
    }
}