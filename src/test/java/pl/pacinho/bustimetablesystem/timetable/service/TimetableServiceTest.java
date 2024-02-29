package pl.pacinho.bustimetablesystem.timetable.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pacinho.bustimetablesystem.bus.model.entity.*;
import pl.pacinho.bustimetablesystem.bus.repository.BusRideRepository;
import pl.pacinho.bustimetablesystem.timetable.model.dto.Timetable;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class TimetableServiceTest {

    @InjectMocks
    private TimetableService timetableService;

    @Mock
    private BusRideRepository busRideRepository;

    @Test
    void emptyOptionalShouldBeGeneratedWhenRouteForGivenBusNumberNotExists() {
        //given
        given(busRideRepository.findAllWithFetchBusAndStops(anyInt())).willReturn(Collections.emptyList());

        //when
        Optional<Timetable> timetableOpt = timetableService.generate(1);

        //then
        assertThat(timetableOpt.isEmpty(), is(true));
    }

    @Test
    void timetableListWithOneElementShouldBeGeneratedWhenExistOneRouteForGivenBusNumber() {
        //given
        BusRoute busRoute = getBusRouteWithThreeStops();
        BusRide busRide = new BusRide(busRoute, LocalTime.now());
        List<BusRide> busRides = List.of(busRide);

        given(busRideRepository.findAllWithFetchBusAndStops(anyInt())).willReturn(busRides);

        //when
        Timetable timetable = timetableService.generate(1)
                .orElse(null);

        //then
        assertThat(timetable, notNullValue());
        assertThat(timetable.getRides(), hasSize(1));
        assertThat(timetable.getRides().get(0).arriveTimes(), hasSize(1));
        assertThat(timetable.getRides().get(0).stops(), hasSize(3));
    }

    @Test
    void timetableListWithOneElementAndEmptyStopListShouldBeGeneratedWhenExistOneRouteWithoutStopsForGivenBusNumber() {
        //given
        BusRoute busRoute = getBusRouteWithThreeStops();
        busRoute.getBusStops().clear();
        BusRide busRide = new BusRide(busRoute, LocalTime.now());
        List<BusRide> busRides = List.of(busRide);

        given(busRideRepository.findAllWithFetchBusAndStops(anyInt())).willReturn(busRides);

        //when
        Timetable timetable = timetableService.generate(1)
                .orElse(null);

        //then
        assertThat(timetable, notNullValue());
        assertThat(timetable.getRides(), hasSize(1));
        assertThat(timetable.getRides().get(0).stops(), empty());
    }

    private BusRoute getBusRouteWithThreeStops() {
        BusRoute busRoute = new BusRoute(mock(Bus.class), 100, 100);
        busRoute.addBusStop(new BusRouteStop(1, new BusStop("Test", "Test"), busRoute, 0));
        busRoute.addBusStop(new BusRouteStop(2, new BusStop("Test2", "Tes2"), busRoute, 5));
        busRoute.addBusStop(new BusRouteStop(3, new BusStop("Test3", "Tes3"), busRoute, 10));
        return busRoute;
    }
}