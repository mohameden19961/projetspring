package mr.supnum.library_app.service.impl;

import lombok.RequiredArgsConstructor;
import mr.supnum.library_app.dto.request.ReservationRequestDTO;
import mr.supnum.library_app.dto.response.ReservationResponseDTO;
import mr.supnum.library_app.entity.Book;
import mr.supnum.library_app.entity.Member;
import mr.supnum.library_app.entity.Reservation;
import mr.supnum.library_app.entity.enums.ReservationStatus;
import mr.supnum.library_app.exception.BusinessException;
import mr.supnum.library_app.exception.ResourceNotFoundException;
import mr.supnum.library_app.repository.BookRepository;
import mr.supnum.library_app.repository.MemberRepository;
import mr.supnum.library_app.repository.ReservationRepository;
import mr.supnum.library_app.service.ReservationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    @Override
    public List<ReservationResponseDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationResponseDTO getReservationById(Long id) {
        return reservationRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id: " + id));
    }

    @Override
    @Transactional
    public ReservationResponseDTO createReservation(ReservationRequestDTO request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + request.getMemberId()));
        
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + request.getBookId()));

        // Logical Check: Member shouldn't have a PENDING reservation for the same book
        if (reservationRepository.existsByMemberAndBookAndStatus(member, book, ReservationStatus.PENDING)) {
            throw new BusinessException("Member already has a pending reservation for this book");
        }

        // Logic: Calculate queue position
        long pendingCount = reservationRepository.countByBookAndStatus(book, ReservationStatus.PENDING);
        int queuePosition = (int) pendingCount + 1;

        Reservation reservation = new Reservation();
        reservation.setMember(member);
        reservation.setBook(book);
        reservation.setQueuePosition(queuePosition);
        reservation.setExpiresAt(LocalDateTime.now().plusDays(7)); // Default 7 days
        reservation.setStatus(ReservationStatus.PENDING);
        reservation.setNotes(request.getNotes());

        return mapToResponse(reservationRepository.save(reservation));
    }

    @Override
    @Transactional
    public void cancelReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id: " + id));
        
        if (reservation.getStatus() != ReservationStatus.PENDING) {
            throw new BusinessException("Only pending reservations can be cancelled");
        }
        
        reservation.setStatus(ReservationStatus.CANCELLED);
        reservationRepository.save(reservation);
    }

    private ReservationResponseDTO mapToResponse(Reservation reservation) {
        ReservationResponseDTO response = new ReservationResponseDTO();
        response.setId(reservation.getId());
        response.setMemberId(reservation.getMember().getId());
        response.setMemberName(reservation.getMember().getFirstName() + " " + reservation.getMember().getLastName());
        response.setBookId(reservation.getBook().getId());
        response.setBookTitle(reservation.getBook().getTitle());
        response.setQueuePosition(reservation.getQueuePosition());
        response.setReservedAt(reservation.getReservedAt());
        response.setExpiresAt(reservation.getExpiresAt());
        response.setStatus(reservation.getStatus());
        if (reservation.getFulfilledByBorrow() != null) {
            response.setFulfilledByBorrowId(reservation.getFulfilledByBorrow().getId());
        }
        response.setNotes(reservation.getNotes());
        return response;
    }
}
