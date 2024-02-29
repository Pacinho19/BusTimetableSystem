package pl.pacinho.bustimetablesystem.timetable.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.pacinho.bustimetablesystem.bus.model.dto.BusDto;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Timetable {

    private BusDto busDto;
    private List<BusRideDto> rides;
}
