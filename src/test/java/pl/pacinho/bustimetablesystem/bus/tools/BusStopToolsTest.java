package pl.pacinho.bustimetablesystem.bus.tools;

import org.junit.jupiter.api.Test;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusRouteStop;

import java.util.Collections;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BusStopToolsTest {

    @Test
    void nullShouldBeReturnedFromFindFirstStopOnTheRouteWhenRouteStopCollectionIsEmpty() {
        //given
        Set<BusRouteStop> busRouteStops = Collections.emptySet();

        //when
        BusRouteStop firstStopOnTheRoute = BusStopTools.findFirstStopOnTheRoute(busRouteStops);

        //then
        assertThat(firstStopOnTheRoute, nullValue());
    }

    @Test
    void busStopWithLowestNumberShouldBeReturnedFromFindFirstStopOnTheRouteWhenRouteStopCollectionHaveManyElementsWithDifferentNumbers() {
        //given
        Set<BusRouteStop> busRouteStops = getBusRouteStopsWithNumberFromOneToFour();
        BusRouteStop firstBusStop = busRouteStops.stream().filter(bs -> bs.getNumber() == 1).findFirst().get();

        //when
        BusRouteStop firstStopOnTheRoute = BusStopTools.findFirstStopOnTheRoute(busRouteStops);

        //then
        assertThat(firstStopOnTheRoute, notNullValue());
        assertThat(firstStopOnTheRoute.getNumber(), equalTo(1));
        assertThat(firstStopOnTheRoute, sameInstance(firstBusStop));
    }

    @Test
    void nullPointerExceptionShouldBeThrownFromFindFirstStopOnTheRouteWhenCollectionOfBusRouteStopsAreNull(){
        //given
        //when
        //then
        assertThrows(NullPointerException.class, () -> BusStopTools.findFirstStopOnTheRoute(null));
    }

    @Test
    void nullPointerExceptionShouldBeThrownFromFindLastStopOnTheRouteWhenCollectionOfBusRouteStopsAreNull(){
        //given
        //when
        //then
        assertThrows(NullPointerException.class, () -> BusStopTools.findLastStopOnTheRoute(null));
    }

    @Test
    void busStopWithHighestNumberShouldBeReturnedFromFindFirstStopOnTheRouteWhenRouteStopCollectionHaveManyElementsWithDifferentNumbers() {
        //given
        Set<BusRouteStop> busRouteStops = getBusRouteStopsWithNumberFromOneToFour();
        BusRouteStop firstBusStop = busRouteStops.stream().filter(bs -> bs.getNumber() == 4).findFirst().get();

        //when
        BusRouteStop firstStopOnTheRoute = BusStopTools.findLastStopOnTheRoute(busRouteStops);

        //then
        assertThat(firstStopOnTheRoute, notNullValue());
        assertThat(firstStopOnTheRoute.getNumber(), equalTo(4));
        assertThat(firstStopOnTheRoute, sameInstance(firstBusStop));
    }

    private static Set<BusRouteStop> getBusRouteStopsWithNumberFromOneToFour() {
        return Set.of(
                new BusRouteStop(1, null, null, null, 0),
                new BusRouteStop(2, null, null, null, 0),
                new BusRouteStop(3, null, null, null, 0),
                new BusRouteStop(4, null, null, null, 0)
        );
    }

    @Test
    void nullShouldBeReturnedFromFindLastStopOnTheRouteWhenRouteStopCollectionIsEmpty() {
        //given
        Set<BusRouteStop> busRouteStops = Collections.emptySet();

        //when
        BusRouteStop firstStopOnTheRoute = BusStopTools.findFirstStopOnTheRoute(busRouteStops);

        //then
        assertThat(firstStopOnTheRoute, nullValue());
    }
}