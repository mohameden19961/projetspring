package mr.supnum.library_app.service.impl;

import lombok.RequiredArgsConstructor;
import mr.supnum.library_app.dto.request.BorrowRequestDTO;
import mr.supnum.library_app.dto.response.BorrowResponseDTO;
import mr.supnum.library_app.entity.BookItem;
import mr.supnum.library_app.entity.Borrow;
import mr.supnum.library_app.entity.Member;
import mr.supnum.library_app.entity.enums.BookStatus;
import mr.supnum.library_app.entity.enums.BorrowStatus;
import mr.supnum.library_app.exception.BusinessException;
import mr.supnum.library_app.exception.ResourceNotFoundException;
import mr.supnum.library_app.repository.BookItemRepository;
import mr.supnum.library_app.repository.BorrowRepository;
import mr.supnum.library_app.repository.MemberRepository;
import mr.supnum.library_app.service.BorrowService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BorrowServiceImpl implements BorrowService {

    private final BorrowRepository borrowRepository;
    private final MemberRepository memberRepository;
    private final BookItemRepository bookItemRepository;

    @Override
    public List<BorrowResponseDTO> getAllBorrows() {
        return borrowRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BorrowResponseDTO getBorrowById(Long id) {
        return borrowRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow not found with id: " + id));
    }

    @Override
    @Transactional
    public BorrowResponseDTO createBorrow(BorrowRequestDTO request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + request.getMemberId()));
        
        BookItem bookItem = bookItemRepository.findById(request.getBookItemId())
                .orElseThrow(() -> new ResourceNotFoundException("BookItem not found with id: " + request.getBookItemId()));

        // Logic: Check if BookItem is AVAILABLE
        if (bookItem.getStatus() != BookStatus.AVAILABLE) {
            throw new BusinessException("Book item is not available for borrowing. Current status: " + bookItem.getStatus());
        }

        // Logic: Check if user exceeded max borrows
        long activeBorrows = borrowRepository.countByMemberAndStatus(member, BorrowStatus.ACTIVE);
        if (member.getMaxBorrows() != null && activeBorrows >= member.getMaxBorrows()) {
            throw new BusinessException("Member has reached the maximum number of borrowed books (" + member.getMaxBorrows() + ")");
        }

        Borrow borrow = new Borrow();
        borrow.setMember(member);
        borrow.setBookItem(bookItem);
        borrow.setBorrowDate(LocalDateTime.now());
        borrow.setReturnDate(LocalDateTime.now().plusDays(15)); // Default 15 days
        borrow.setStatus(BorrowStatus.ACTIVE);
        borrow.setFinePerDay(request.getFinePerDay());

        // Update BookItem status
        bookItem.setStatus(BookStatus.BORROWED);
        bookItemRepository.save(bookItem);

        return mapToResponse(borrowRepository.save(borrow));
    }

    @Override
    @Transactional
    public BorrowResponseDTO returnBook(Long id) {
        Borrow borrow = borrowRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow not found with id: " + id));
        
        if (borrow.getStatus() == BorrowStatus.RETURNED) {
            throw new BusinessException("This book has already been returned");
        }

        borrow.setReturnedAt(LocalDateTime.now());
        borrow.setStatus(BorrowStatus.RETURNED);

        // Update BookItem status
        BookItem bookItem = borrow.getBookItem();
        bookItem.setStatus(BookStatus.AVAILABLE);
        bookItemRepository.save(bookItem);

        return mapToResponse(borrowRepository.save(borrow));
    }

    @Override
    @Transactional
    public BorrowResponseDTO renewBorrow(Long id) {
        Borrow borrow = borrowRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow not found with id: " + id));

        if (borrow.getStatus() != BorrowStatus.ACTIVE) {
            throw new BusinessException("Only active borrows can be renewed");
        }

        if (borrow.getRenewalCount() >= 3) {
            throw new BusinessException("Maximum renewal count (3) reached for this borrow");
        }

        borrow.setRenewalCount(borrow.getRenewalCount() + 1);
        borrow.setReturnDate(borrow.getReturnDate().plusDays(15)); // Extend by 15 days

        return mapToResponse(borrowRepository.save(borrow));
    }

    private BorrowResponseDTO mapToResponse(Borrow borrow) {
        BorrowResponseDTO response = new BorrowResponseDTO();
        response.setId(borrow.getId());
        response.setMemberId(borrow.getMember().getId());
        response.setMemberName(borrow.getMember().getFirstName() + " " + borrow.getMember().getLastName());
        response.setBookItemId(borrow.getBookItem().getId());
        response.setBarcode(borrow.getBookItem().getBarcode());
        response.setBookTitle(borrow.getBookItem().getBook().getTitle());
        if (borrow.getReservation() != null) {
            response.setReservationId(borrow.getReservation().getId());
        }
        response.setBorrowDate(borrow.getBorrowDate());
        response.setReturnDate(borrow.getReturnDate());
        response.setReturnedAt(borrow.getReturnedAt());
        response.setStatus(borrow.getStatus());
        response.setRenewalCount(borrow.getRenewalCount());
        response.setFinePerDay(borrow.getFinePerDay());
        response.setArchived(borrow.isArchived());
        return response;
    }
}
