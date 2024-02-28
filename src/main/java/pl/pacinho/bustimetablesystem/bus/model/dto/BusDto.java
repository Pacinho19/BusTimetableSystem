package pl.pacinho.bustimetablesystem.bus.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class BusDto {

    final int number;
    final int numberOfSeats;
    final int numberOfStandingSeats;
}
