package pl.pacinho.bustimetablesystem.timetable.tools;

import org.junit.jupiter.api.Test;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusRouteStop;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusStop;
import pl.pacinho.bustimetablesystem.timetable.model.FilterBusRouteDto;

import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BusRideFilterTest {

    @Test
    void nullPointerExceptionShouldBeReturnedWhenFromParamIsNull() {
        //given
        Set<BusRouteStop> busStops = getBusStopsWithThreeDifferentNamesAndAddresses();
        String from = null;
        String to = "aaa";

        //when
        //then
        assertThrows(NullPointerException.class, () -> BusRideFilter.filterBusRoute(busStops, from, to));
    }

    @Test
    void nullPointerExceptionShouldBeReturnedWhenToParamIsNull() {
        //given
        Set<BusRouteStop> busStops = getBusStopsWithThreeDifferentNamesAndAddresses();
        String from = "aaa";
        String to = null;

        //when
        //then
        assertThrows(NullPointerException.class, () -> BusRideFilter.filterBusRoute(busStops, from, to));
    }

    @Test
    void emptyCollectionShouldBeReturnedWhenBusStopWithGivenValueNamedFromNotExists() {
        //given
        Set<BusRouteStop> busStops = getBusStopsWithThreeDifferentNamesAndAddresses();
        String from = "zzzName";
        String to = "bbbAddress";

        //when
        List<FilterBusRouteDto> filteredRoutes = BusRideFilter.filterBusRoute(busStops, from, to);

        //then
        assertThat(filteredRoutes, empty());
    }

    @Test
    void emptyCollectionShouldBeReturnedWhenBusStopWithGivenValueNamedToNotExists() {
        //given
        Set<BusRouteStop> busStops = getBusStopsWithThreeDifferentNamesAndAddresses();
        String from = "aaaName";
        String to = "zzzAddress";

        //when
        List<FilterBusRouteDto> filteredRoutes = BusRideFilter.filterBusRoute(busStops, from, to);

        //then
        assertThat(filteredRoutes, empty());
    }

    @Test
    void oneRouteShouldBeReturnedWhenOnlyOneBusStopContainsGivenValueNamedFromAndOnlyOneBusStopContainsGivenValueNamedTo() {
        //given
        Set<BusRouteStop> busStops = getBusStopsWithThreeDifferentNamesAndAddresses();
        String from = "aaaName";
        String to = "bbbAddress";

        //when
        List<FilterBusRouteDto> filteredRoutes = BusRideFilter.filterBusRoute(busStops, from, to);

        //then
        assertThat(filteredRoutes, hasSize(1));
    }

    @Test
    void twoRouteShouldBeReturnedWhenOnlyOneBusStopContainsGivenValueNamedFromAndTwoBusStopContainsGivenValueNamedTo() {
        //given
        Set<BusRouteStop> busStops = getBusStopsWithThreeDifferentNamesAndAddresses();
        String from = "aaa";
        String to = "Name";

        //when
        List<FilterBusRouteDto> filteredRoutes = BusRideFilter.filterBusRoute(busStops, from, to);

        //then
        assertThat(filteredRoutes, hasSize(2));
    }

    @Test
    void twoRouteShouldBeReturnedWhenTwoBusStopContainsGivenValueNamedFromAndOnlyOneBusStopContainsGivenValueNamedTo() {
        //given
        Set<BusRouteStop> busStops = getBusStopsWithThreeDifferentNamesAndAddresses();
        String from = "Name";
        String to = "cccAddress";

        //when
        List<FilterBusRouteDto> filteredRoutes = BusRideFilter.filterBusRoute(busStops, from, to);

        //then
        assertThat(filteredRoutes, hasSize(2));
    }

    @Test
    void fourRouteShouldBeReturnedWhenTwoBusStopContainsGivenValueNamedFromAndTwoBusStopContainsGivenValueNamedTo() {
        //given
        Set<BusRouteStop> busStops = getBusStopsWithFourDifferentNamesAndAddresses();
        String from = "Name";
        String to = "Address";

        //when
        List<FilterBusRouteDto> filteredRoutes = BusRideFilter.filterBusRoute(busStops, from, to);

        //then
        assertThat(filteredRoutes, hasSize(4));
    }

    @Test
    void threeBusStopsShouldBeReturnedBetweenInitialBusStopNumerOneAndFinalBusStopNumberThree() {
        //given
        Set<BusRouteStop> busStops = getBusStopsWithFourDifferentNamesAndAddresses();

        //when
        List<BusRouteStop> routeBetweenStops = BusRideFilter.getRouteBetweenStops(busStops, 1, 3);

        //then
        assertThat(routeBetweenStops, hasSize(3));
    }

    @Test
    void oneBusStopsShouldBeReturnedBetweenBusStopsWithTheSameBusStopNumber() {
        //given
        Set<BusRouteStop> busStops = getBusStopsWithFourDifferentNamesAndAddresses();

        //when
        List<BusRouteStop> routeBetweenStops = BusRideFilter.getRouteBetweenStops(busStops, 1, 1);

        //then
        assertThat(routeBetweenStops, hasSize(1));
    }

    @Test
    void emptyCollectionShouldBeReturnedWhenGivenInitialBusStopNumberIsGreaterThanFinalBusStopNumber() {
        //given
        Set<BusRouteStop> busStops = getBusStopsWithFourDifferentNamesAndAddresses();

        //when
        List<BusRouteStop> routeBetweenStops = BusRideFilter.getRouteBetweenStops(busStops, 2, 1);

        //then
        assertThat(routeBetweenStops, empty());
    }


    private static Set<BusRouteStop> getBusStopsWithThreeDifferentNamesAndAddresses() {
        return Set.of(
                new BusRouteStop(1, new BusStop("aaaName", "aaaAddress"), null, 0),
                new BusRouteStop(2, new BusStop("bbbName", "bbbAddress"), null, 1),
                new BusRouteStop(3, new BusStop("cccName", "cccAddress"), null, 2)
        );
    }

    private static Set<BusRouteStop> getBusStopsWithFourDifferentNamesAndAddresses() {
        return Set.of(
                new BusRouteStop(1, new BusStop("aaaName", "aaaAddress"), null, 0),
                new BusRouteStop(2, new BusStop("bbbName", "bbb"), null, 1),
                new BusRouteStop(3, new BusStop("ccc", "cccAddress"), null, 2),
                new BusRouteStop(4, new BusStop("ddd", "dddAddress"), null, 3)
        );
    }


}