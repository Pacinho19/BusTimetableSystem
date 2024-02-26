package pl.pacinho.bustimetablesystem.bus.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_stop_id")
    private BusStop busStop;

    private LocalTime arrivalTime;
    private LocalTime departureTime;

    public BusRouteStop(int number, BusStop busStop, LocalTime arrivalTime, LocalTime departureTime) {
        this.number = number;
        this.busStop = busStop;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }
}
