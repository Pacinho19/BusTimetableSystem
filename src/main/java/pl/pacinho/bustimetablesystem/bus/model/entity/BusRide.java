package pl.pacinho.bustimetablesystem.bus.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@NoArgsConstructor
@Getter
@Entity
public class BusRide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade =  CascadeType.MERGE)
    @JoinColumn(name = "bus_route_id")
    private BusRoute busRoute;

    private LocalTime arriveTime;

    public BusRide(Bus bus, BusRoute busRoute, LocalTime arriveTime) {
        this.bus = bus;
        this.busRoute = busRoute;
        this.arriveTime = arriveTime;
    }
}
