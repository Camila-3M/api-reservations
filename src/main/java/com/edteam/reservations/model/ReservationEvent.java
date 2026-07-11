package com.edteam.reservations.model;

import java.util.List;
import java.util.Objects;

public final class ReservationEvent {

    private final String id;
    private final String passengerName;
    private final Double price;
    private final List<String> emails;

    public ReservationEvent(String id, String passengerName, Double price, List<String> emails) {
        this.id = id;
        this.passengerName = passengerName;
        this.price = price;
        // Copia defensiva en el constructor: si la lista original es null, se guarda una lista vacia e inmutable.
        this.emails = (emails == null) ? List.of() : List.copyOf(emails);
    }

    public String getId() {
        return id;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public Double getPrice() {
        return price;
    }

    public List<String> getEmails() {
        // Copia defensiva en el getter: se retorna una nueva lista inmutable cada vez que se invoca.
        return List.copyOf(emails);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReservationEvent)) {
            return false;
        }
        ReservationEvent that = (ReservationEvent) o;
        return Objects.equals(id, that.id)
                && Objects.equals(passengerName, that.passengerName)
                && Objects.equals(price, that.price)
                && Objects.equals(emails, that.emails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passengerName, price, emails);
    }

    @Override
    public String toString() {
        return "ReservationEvent{" +
                "id='" + id + '\'' +
                ", passengerName='" + passengerName + '\'' +
                ", price=" + price +
                ", emails=" + emails +
                '}';
    }
}
