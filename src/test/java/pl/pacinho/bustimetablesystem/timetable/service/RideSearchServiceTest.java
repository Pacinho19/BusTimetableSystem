package pl.pacinho.bustimetablesystem.timetable.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pacinho.bustimetablesystem.bus.model.dto.BusStopDto;
import pl.pacinho.bustimetablesystem.bus.model.entity.*;
import pl.pacinho.bustimetablesystem.bus.repository.BusRideRepository;
import pl.pacinho.bustimetablesystem.exception.InvalidBusStopNameException;
import pl.pacinho.bustimetablesystem.timetable.model.RideSearchResultDto;

import java.time.LocalTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RideSearchServiceTest {

    @InjectMocks
    private RideSearchService rideSearchService;

    @Mock
    private BusRideRepository busRideRepository;

    @Test
    void invalidBusStopNameExceptionShouldBeThrownWhenGivenValueNamedFromIsNull() {
        //given
        String from = null;
        String to = "aa";

        //when
        //then
        assertThrows(InvalidBusStopNameException.class, () -> rideSearchService.search(from, to));
    }

    @Test
    void invalidBusStopNameExceptionShouldBeThrownWhenGivenValueNamedFromIsEmpty() {
        //given
        String from = "";
        String to = "aa";

        //when
        //then
        assertThrows(InvalidBusStopNameException.class, () -> rideSearchService.search(from, to));
    }

    @Test
    void invalidBusStopNameExceptionShouldBeThrownWhenGivenValueNamedToIsNull() {
        //given
        String from = "aa";
        String to = null;

        //when
        //then
        assertThrows(InvalidBusStopNameException.class, () -> rideSearchService.search(from, to));
    }

    @Test
    void invalidBusStopNameExceptionShouldBeThrownWhenGivenValueNamedToIsEmpty() {
        //given
        String from = "aa";
        String to = "";

        //when
        //then
        assertThrows(InvalidBusStopNameException.class, () -> rideSearchService.search(from, to));
    }

    @Test
    void twoSearchResultShouldBeReturnedWhenOneInitialBusStopExistsWithGivenPartOfNameAndTwoFinalBusStopsExistsWithGivenPartOfName() {
        //given
        LocalTime startTime = LocalTime.now();
        List<BusRide> rides = List.of(new BusRide(getTestBusRoute(), startTime));
        given(busRideRepository.findAllWithFetchBusAndStops()).willReturn(rides);
        String from = "Test_1";
        String to = "Stop_";

        //when
        List<RideSearchResultDto> searchResults = rideSearchService.search(from, to);

        //then
        assertThat(searchResults, hasSize(2));

        assertThat(searchResults, containsInAnyOrder(
                hasProperty("totalTime", is(2)),
                hasProperty("totalTime", is(3))
        ));

        assertThat(searchResults, containsInAnyOrder(
                hasProperty("stops", hasSize(3)),
                hasProperty("stops", hasSize(4))
        ));

        assertThat(searchResults, containsInAnyOrder(
                hasProperty("times", contains(startTime)),
                hasProperty("times", contains(startTime))
        ));

        assertThat(searchResults, containsInAnyOrder(
                        hasProperty("stops", contains(
                                        new BusStopDto("Test_1", "Test_1"),
                                        new BusStopDto("Test_2", "Test_2"),
                                        new BusStopDto("Stop_1", "Stop_1")
                                )
                        ),
                        hasProperty("stops", contains(
                                        new BusStopDto("Test_1", "Test_1"),
                                        new BusStopDto("Test_2", "Test_2"),
                                        new BusStopDto("Stop_1", "Stop_1"),
                                        new BusStopDto("Stop_2", "Stop_2")
                                )
                        )
                )
        );

    }

    @Test
    void emptySearchResultCollectionShouldBeReturnedWhenBusStopsByGivenInitialBusStopNotExists() {
        //given
        LocalTime startTime = LocalTime.now();
        List<BusRide> rides = List.of(new BusRide(getTestBusRoute(), startTime));
        given(busRideRepository.findAllWithFetchBusAndStops()).willReturn(rides);
        String from = "fake";
        String to = "Stop_";

        //when
        List<RideSearchResultDto> searchResults = rideSearchService.search(from, to);

        //then
        assertThat(searchResults, empty());
    }

    private BusRoute getTestBusRoute() {
        BusRoute busRoute = new BusRoute(new Bus(1, 50, 10), 100, 30);
        busRoute.addBusStop(new BusRouteStop(1, new BusStop("Test_1", "Test_1"), busRoute, 0));
        busRoute.addBusStop(new BusRouteStop(2, new BusStop("Test_2", "Test_2"), busRoute, 1));
        busRoute.addBusStop(new BusRouteStop(3, new BusStop("Stop_1", "Stop_1"), busRoute, 2));
        busRoute.addBusStop(new BusRouteStop(4, new BusStop("Stop_2", "Stop_2"), busRoute, 3));
        return busRoute;
    }
}