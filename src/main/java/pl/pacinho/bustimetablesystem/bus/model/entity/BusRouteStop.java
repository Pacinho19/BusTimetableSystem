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

    private LocalTime arrivalTime;
    private LocalTime departureTime;

    public BusRouteStop(int number, BusStop busStop, BusRoute busRoute, LocalTime arrivalTime, LocalTime departureTime) {
        this.number = number;
        this.busStop = busStop;
        this.busRoute = busRoute;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }
}
