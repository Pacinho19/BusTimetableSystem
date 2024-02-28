package pl.pacinho.bustimetablesystem.bus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class BusWithRidesDto extends BusDto {

    private List<BusRouteDto> rides;

    public BusWithRidesDto(int number, int numberOfSeats, int numberOfStandingSeats, List<BusRouteDto> rides) {
        super(number, numberOfSeats, numberOfStandingSeats);
        this.rides = rides;
    }
}
