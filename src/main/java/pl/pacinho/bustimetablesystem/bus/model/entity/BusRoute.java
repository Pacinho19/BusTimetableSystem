package pl.pacinho.bustimetablesystem.bus.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    public BusRoute(Bus bus, Set<BusRouteStop> busStops) {
        this.bus = bus;
        this.busStops = busStops;
    }

    public void addBusStop(BusRouteStop busRouteStop) {
        this.busStops.add(busRouteStop);
        busRouteStop.setBusRoute(this);
    }
}
