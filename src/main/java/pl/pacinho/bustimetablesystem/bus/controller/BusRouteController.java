package pl.pacinho.bustimetablesystem.bus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.pacinho.bustimetablesystem.bus.model.dto.BusDto;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusRoute;
import pl.pacinho.bustimetablesystem.bus.service.BusRouteService;
import pl.pacinho.bustimetablesystem.bus.service.BusService;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/bus-route")
@RestController
public class BusRouteController {

    private final BusRouteService busRouteService;

    @GetMapping("/bus")
    ResponseEntity<BusDto> findAll(@PathParam("busId") int busId) {
        Optional<BusDto> routesByBus = busRouteService.findAll(busId);
        return routesByBus.map(
                ResponseEntity::ok
        ).orElse(ResponseEntity.notFound().build());
    }

}
