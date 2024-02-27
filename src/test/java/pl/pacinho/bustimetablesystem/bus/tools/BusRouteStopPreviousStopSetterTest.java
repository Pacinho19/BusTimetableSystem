package pl.pacinho.bustimetablesystem.bus.tools;

import org.junit.jupiter.api.Test;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusRouteStop;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class BusRouteStopPreviousStopSetterTest {

    @Test
    void previousStopShouldBeNullWhenGivenStopNumberIsOne() {
        //given
        BusRouteStop firstStop = new BusRouteStop(1, null, null, 0);
        List<BusRouteStop> stops = List.of(firstStop);

        //when
        BusRouteStopPreviousStopSetter.setPreviousStop(firstStop, stops);

        //then
        assertThat(firstStop.getPreviousStop(), nullValue());
    }

    @Test
    void previousStopShouldBeEqualToStopWithNumberOneWhenGivenStopNumberIsTwo() {
        //given
        BusRouteStop firstStop = new BusRouteStop(1, null, null, 0);
        BusRouteStop secondStop = new BusRouteStop(2, null, null, 1);
        List<BusRouteStop> stops = List.of(firstStop, secondStop);

        //when
        BusRouteStopPreviousStopSetter.setPreviousStop(secondStop, stops);

        //then
        assertThat(secondStop.getPreviousStop(), notNullValue());
        assertThat(secondStop.getPreviousStop(), sameInstance(firstStop));
    }
}