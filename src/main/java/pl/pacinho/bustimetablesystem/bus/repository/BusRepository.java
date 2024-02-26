package pl.pacinho.bustimetablesystem.bus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pacinho.bustimetablesystem.bus.model.entity.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer> {
}
