package pl.pacinho.bustimetablesystem.bus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusRoute;

@Repository
public interface BusRouteRepository extends JpaRepository<BusRoute, Integer> {

}
