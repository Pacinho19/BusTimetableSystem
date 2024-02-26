package pl.pacinho.bustimetablesystem.bus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pacinho.bustimetablesystem.bus.service.BusService;

@RequiredArgsConstructor
@RequestMapping("/bus")
@RestController
public class BusController {

    private final BusService busService;

}
