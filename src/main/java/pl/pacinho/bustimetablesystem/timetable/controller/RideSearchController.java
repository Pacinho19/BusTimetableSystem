package pl.pacinho.bustimetablesystem.timetable.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pacinho.bustimetablesystem.timetable.model.RideSearchResultDto;
import pl.pacinho.bustimetablesystem.timetable.service.RideSearchService;

import javax.websocket.server.PathParam;
import java.util.List;

@RequestMapping("/search")
@RequiredArgsConstructor
@RestController
public class RideSearchController {

    private final RideSearchService rideSearchService;

    @GetMapping(params = {"from", "to"})
    ResponseEntity<?> search(@PathParam("from") String from, @PathParam("to") String to) {
        List<RideSearchResultDto> searchResult = rideSearchService.search(from, to);
        return searchResult.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(searchResult);
    }
}
