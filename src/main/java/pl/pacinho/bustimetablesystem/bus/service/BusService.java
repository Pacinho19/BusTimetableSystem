package pl.pacinho.bustimetablesystem.bus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pacinho.bustimetablesystem.bus.model.entity.Bus;
import pl.pacinho.bustimetablesystem.bus.repository.BusRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BusService {

    private final BusRepository busRepository;

    public List<Integer> getBusNumbers() {
        return busRepository.findAll()
                .stream()
                .map(Bus::getNumber)
                .collect(Collectors.toList());
    }
}
