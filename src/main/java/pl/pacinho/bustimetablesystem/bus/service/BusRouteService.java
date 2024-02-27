package pl.pacinho.bustimetablesystem.bus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pacinho.bustimetablesystem.bus.repository.BusRouteRepository;

@RequiredArgsConstructor
@Service
public class BusRouteService {

    private final BusRouteRepository busRouteRepository;

}
