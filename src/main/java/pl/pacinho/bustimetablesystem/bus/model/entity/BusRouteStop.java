package pl.pacinho.bustimetablesystem.bus.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@NoArgsConstructor
@Getter
@Entity
public class BusRouteStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int number;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "bus_stop_id")
    private BusStop busStop;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "bus_route_id")
    private BusRoute busRoute;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "previous_bus_route_stop_id")
    private BusRouteStop previousStop;

    @Setter
    private int minutesFromStart;

    public BusRouteStop(int number, BusStop busStop, BusRoute busRoute, BusRouteStop previousStop, int minutesFromStart) {
        this.number = number;
        this.busStop = busStop;
        this.busRoute = busRoute;
        this.previousStop = previousStop;
        this.minutesFromStart = minutesFromStart;
    }
}
