package pl.pacinho.bustimetablesystem.bus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pacinho.bustimetablesystem.bus.model.dto.BusWithRidesDto;
import pl.pacinho.bustimetablesystem.bus.model.dto.mapper.BusMapper;
import pl.pacinho.bustimetablesystem.bus.repository.BusRideRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BusRideService {

    private final BusRideRepository busRideRepository;

    public Optional<BusWithRidesDto> findAll(int busId) {
        return BusMapper.convertWithRides(
                busRideRepository.findAllWithFetchBusAndStops(busId)
        );
    }

}
