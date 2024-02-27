package pl.pacinho.bustimetablesystem.bus.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "bus_route_id")
    private final Set<BusRouteStop> busStops = new HashSet<>();

    private int distance;
    private int summaryTime;

    public BusRoute(int distance, int summaryTime) {
        this.distance = distance;
        this.summaryTime = summaryTime;
    }

    public void addBusStop(BusRouteStop busRouteStop) {
        this.busStops.add(busRouteStop);
        busRouteStop.setBusRoute(this);
    }
}
