package pl.pacinho.bustimetablesystem.bus.model.dto.mapper;

import pl.pacinho.bustimetablesystem.bus.model.dto.BusStopDto;
import pl.pacinho.bustimetablesystem.bus.model.entity.BusStop;

public class BusStopMapper {
    public static BusStopDto convert(BusStop busStop) {
        return new BusStopDto(
                busStop.getName(),
                busStop.getAddress()
        );
    }
}
