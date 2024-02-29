package pl.pacinho.bustimetablesystem.bus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pacinho.bustimetablesystem.bus.model.dto.BusWithRidesDto;
import pl.pacinho.bustimetablesystem.bus.service.BusRideService;

import javax.websocket.server.PathParam;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/bus-ride")
@RestController
public class BusRideController {

    private final BusRideService busRideService;

    @GetMapping("/bus")
    ResponseEntity<BusWithRidesDto> findAll(@PathParam("busNumber") int busNumber) {
        Optional<BusWithRidesDto> routesByBus = busRideService.findAll(busNumber);
        return routesByBus.map(
                ResponseEntity::ok
        ).orElse(ResponseEntity.notFound().build());
    }

}
