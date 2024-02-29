package pl.pacinho.bustimetablesystem.bus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusRide;

import java.util.List;

@Repository
public interface BusRideRepository extends JpaRepository<BusRide, Integer> {

    @Query(value = """
            select distinct br
            from BusRide br
            join fetch br.busRoute brr
            join fetch brr.bus b
            join fetch brr.busStops brs
            join fetch brs.busStop bs
            where b.number = :busNumber
            """)
    List<BusRide> findAllWithFetchBusAndStops(@Param("busNumber") int busNumber);

    @Query(value = """
            select distinct br
            from BusRide br
            join fetch br.busRoute brr
            join fetch brr.bus b
            join fetch brr.busStops brs
            join fetch brs.busStop bs
            """)
    List<BusRide> findAllWithFetchBusAndStops();
}
