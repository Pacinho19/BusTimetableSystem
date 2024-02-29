package pl.pacinho.bustimetablesystem.timetable.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.pacinho.bustimetablesystem.bus.service.BusService;
import pl.pacinho.bustimetablesystem.timetable.model.dto.BusStopTimeDto;
import pl.pacinho.bustimetablesystem.timetable.model.dto.Timetable;
import pl.pacinho.bustimetablesystem.timetable.service.TimetableService;

import javax.websocket.server.PathParam;

@RequestMapping("/timetable")
@RequiredArgsConstructor
@Controller
public class TimetableController {

    private final TimetableService timetableService;
    private final BusService busService;

    @ResponseBody
    @GetMapping(value = "/bus", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> findAll(@PathParam("busNumber") int busNumber) {
        return timetableService.generate(busNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{busNumber}")
    String findAll(@PathVariable("busNumber") Integer busNumber,
                   @RequestParam(value = "stop", required = false) String stopName,
                   @RequestParam(value = "ride", required = false, defaultValue = "0") int ride,
                   Model model) {
        Timetable timetable = timetableService.generate(busNumber).orElse(null);
        model.addAttribute("timetable", timetable);
        model.addAttribute("busNumber", busNumber);
        model.addAttribute("busLines", busService.getBusNumbers());
        model.addAttribute("extraMinutes", stopName == null || timetable == null ? 0
                : timetable.getRides().get(ride).stops().stream()
                .filter(s -> s.busStop().name().equals(stopName))
                .map(BusStopTimeDto::time)
                .findFirst()
                .orElse(0));
        return "bus-timetable";
    }

}
