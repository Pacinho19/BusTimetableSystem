package pl.pacinho.bustimetablesystem.timetable.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pacinho.bustimetablesystem.timetable.model.dto.Timetable;
import pl.pacinho.bustimetablesystem.timetable.service.TimetableService;

import javax.websocket.server.PathParam;
import java.util.List;

@RequestMapping("/timetable")
@RequiredArgsConstructor
@RestController
public class TimetableController {

    private final TimetableService timetableService;

    @GetMapping("/bus")
    ResponseEntity<List<Timetable>> findAll(@PathParam("busId") int busId) {
        List<Timetable> timetables = timetableService.generate(busId);
        return timetables.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(timetables);
    }

}
