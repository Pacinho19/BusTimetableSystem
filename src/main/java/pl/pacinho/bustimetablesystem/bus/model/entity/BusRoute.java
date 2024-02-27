package pl.pacinho.bustimetablesystem.bus.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Entity
public class BusRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @Setter
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "bus_route_id")
    private Set<BusRouteStop> busStops = new HashSet<>();

    private LocalTime departureTime;

    public BusRoute(Bus bus, Set<BusRouteStop> busStops, LocalTime departureTime) {
        this.bus = bus;
        this.busStops = busStops;
        this.departureTime = departureTime;
    }

    public void addBusStop(BusRouteStop busRouteStop) {
        this.busStops.add(busRouteStop);
        busRouteStop.setBusRoute(this);
    }
}
