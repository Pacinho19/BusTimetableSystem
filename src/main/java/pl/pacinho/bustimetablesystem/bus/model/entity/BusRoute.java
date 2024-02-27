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
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "bus_route_id")
    private Set<BusRouteStop> busStops = new HashSet<>();

    private int distance;
    private int summaryTime;

    public BusRoute(Set<BusRouteStop> busStops, int distance, int summaryTime) {
        this.busStops = busStops;
        this.distance = distance;
        this.summaryTime = summaryTime;
    }

    public void addBusStop(BusRouteStop busRouteStop) {
        this.busStops.add(busRouteStop);
        busRouteStop.setBusRoute(this);
    }
}
