package com.edteam.reservations.functional;

import com.edteam.reservations.model.ReservationEvent;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Clase utilitaria que centraliza la logica funcional (lambdas) usada
 * para validar y procesar los eventos de reserva.
 */
public final class ReservationFilters {

    private ReservationFilters() {
        // Clase utilitaria: no debe instanciarse.
    }

    /**
     * Predicate que valida que una reserva sea "valida":
     *  - El precio debe ser mayor a 0.
     *  - La lista de correos no debe estar vacia.
     */
    public static final Predicate<ReservationEvent> IS_VALID_RESERVATION = event ->
            event.getPrice() != null
                    && event.getPrice() > 0
                    && event.getEmails() != null
                    && !event.getEmails().isEmpty();

    /**
     * Consumer que imprime por consola el evento de reserva que fue procesado.
     */
    public static final Consumer<ReservationEvent> PRINT_RESERVATION = event ->
            System.out.println("[Reserva procesada] " + event);
}
