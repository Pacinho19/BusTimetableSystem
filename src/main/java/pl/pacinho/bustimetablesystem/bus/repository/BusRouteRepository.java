package pl.pacinho.bustimetablesystem.bus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusRoute;

import java.util.List;

@Repository
public interface BusRouteRepository extends JpaRepository<BusRoute, Integer> {

    @Query(value = """
            select distinct br
            from BusRoute br
            join fetch br.bus b
            join fetch br.busStops brs
            join fetch brs.busStop bs
            where b.id = :busId
            """)
    List<BusRoute> findAll(@Param("busId") int busId);
}
