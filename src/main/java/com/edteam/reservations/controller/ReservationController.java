package com.edteam.reservations.controller;

import com.edteam.reservations.functional.ReservationFilters;
import com.edteam.reservations.model.ReservationEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@RestController
public class ReservationController {

    /**
     * Reserva generica que se retorna cuando, tras aplicar el filtro,
     * el flujo queda vacio (operador defaultIfEmpty).
     */
    private static final ReservationEvent DEFAULT_RESERVATION = new ReservationEvent(
            "DEFAULT-000", "Sin reservas disponibles", 0.0, List.of());

    @GetMapping(value = "/api/reservations/stream", produces = "text/event-stream")
    public Flux<ReservationEvent> streamReservations() {
        return Flux.just(
                        // 3 reservas validas: precio > 0 y con al menos un email
                        new ReservationEvent("R-001", "Carlos Perez", 250.0, List.of("carlos.perez@mail.com")),
                        new ReservationEvent("R-002", "Ana Torres", 320.50, List.of("ana.torres@mail.com", "ana2@mail.com")),
                        new ReservationEvent("R-003", "Luis Fernandez", 95.0, List.of("luis.fernandez@mail.com")),

                        // 2 reservas invalidas: una con precio <= 0 y otra sin emails
                        new ReservationEvent("R-004", "Maria Lopez", 0.0, List.of("maria.lopez@mail.com")),
                        new ReservationEvent("R-005", "Juan Diaz", 180.0, List.of())
                )
                .filter(ReservationFilters.IS_VALID_RESERVATION)
                .doOnNext(ReservationFilters.PRINT_RESERVATION)
                .defaultIfEmpty(DEFAULT_RESERVATION)
                // OPCIONAL: pequeno retraso entre elementos, solo para que al probar con
                // curl se note visualmente que el flujo llega de a poco (no bloqueante).
                // Si se prefiere no usarlo, basta con eliminar esta linea.
                .delayElements(Duration.ofMillis(400));
    }
}
