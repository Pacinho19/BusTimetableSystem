package pl.pacinho.bustimetablesystem.bus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pacinho.bustimetablesystem.bus.repository.BusRepository;

@RequiredArgsConstructor
@Service
public class BusService {

    private final BusRepository busRepository;
}
