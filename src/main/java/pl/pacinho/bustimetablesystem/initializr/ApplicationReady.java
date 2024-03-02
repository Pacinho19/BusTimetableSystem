package pl.pacinho.bustimetablesystem.initializr;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ApplicationReady {

    @EventListener(ApplicationReadyEvent.class)
    void startApp() {
        log.info("Application started!");
    }
}
