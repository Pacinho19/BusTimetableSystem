package pl.pacinho.bustimetablesystem.bus.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Entity
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int number;
    private int numberOfSeats;
    private int numberOfStandingSeats;

    public Bus(int number, int numberOfSeats, int numberOfStandingSeats) {
        this.number = number;
        this.numberOfSeats = numberOfSeats;
        this.numberOfStandingSeats = numberOfStandingSeats;
    }
}
