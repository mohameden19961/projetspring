package mr.supnum.library_app.service;

import mr.supnum.library_app.dto.request.ReservationRequestDTO;
import mr.supnum.library_app.dto.response.ReservationResponseDTO;

import java.util.List;

public interface ReservationService {
    List<ReservationResponseDTO> getAllReservations();
    ReservationResponseDTO getReservationById(Long id);
    ReservationResponseDTO createReservation(ReservationRequestDTO request);
    void cancelReservation(Long id);
}
