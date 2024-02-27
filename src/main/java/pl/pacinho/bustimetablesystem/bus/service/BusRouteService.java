package pl.pacinho.bustimetablesystem.bus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pacinho.bustimetablesystem.bus.model.dto.BusDto;
import pl.pacinho.bustimetablesystem.bus.model.dto.mapper.BusMapper;
import pl.pacinho.bustimetablesystem.bus.repository.BusRouteRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BusRouteService {

    private final BusRouteRepository busRouteRepository;

    public Optional<BusDto> findAll(int busId) {
        return BusMapper.convert(
                busRouteRepository.findAllWithFetchBusAndStops(busId)
        );
    }
}
