package com.alasdeplata.dto.checkout;

import java.util.List;

public record ReservationCheckoutRequest(
    PaymentData payment,
    ReservationData reservation) {
  public record PaymentData(
      String paymentMethod,
      String numberCard,
      String ownerCard,
      String month,
      String year,
      String cvv,
      Boolean termsAccepted) {
  }

  public record ReservationData(
      Long flightId,
      Long fareId,
      List<PassengerData> passengers,
      String seat,
      Integer seatExtraPrice,
      List<Long> services) {
  }

  public record PassengerData(
      String firstName,
      String lastName,
      String gender,
      String birthDay,
      String birthMonth,
      String birthYear,
      String documentType,
      String documentNumber,
      String documentExpiration,
      String email,
      String phone) {
  }
}
