package mr.supnum.library_app.repository;

import mr.supnum.library_app.entity.Book;
import mr.supnum.library_app.entity.Member;
import mr.supnum.library_app.entity.Reservation;
import mr.supnum.library_app.entity.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
    boolean existsByMemberAndBookAndStatus(Member member, Book book, ReservationStatus status);
    
    long countByBookAndStatus(Book book, ReservationStatus status);
}
