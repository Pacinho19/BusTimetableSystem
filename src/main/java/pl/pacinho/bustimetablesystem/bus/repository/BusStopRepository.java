package pl.pacinho.bustimetablesystem.bus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusStop;

import java.util.Optional;

@Repository
public interface BusStopRepository extends JpaRepository<BusStop, Integer> {
    Optional<BusStop> findByName(String name);
}
