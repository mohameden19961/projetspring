package mr.supnum.library_app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mr.supnum.library_app.dto.request.ReservationRequestDTO;
import mr.supnum.library_app.dto.response.ReservationResponseDTO;
import mr.supnum.library_app.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping
    public List<ReservationResponseDTO> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public ReservationResponseDTO getReservation(@PathVariable Long id) {
        return reservationService.getReservationById(id);
    }

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> createReservation(@Valid @RequestBody ReservationRequestDTO request) {
        return new ResponseEntity<>(reservationService.createReservation(request), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/cancel")
    public void cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
    }
}
