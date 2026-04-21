package mr.supnum.library_app.service;

import mr.supnum.library_app.dto.request.BorrowRequestDTO;
import mr.supnum.library_app.dto.response.BorrowResponseDTO;

import java.util.List;

public interface BorrowService {
    List<BorrowResponseDTO> getAllBorrows();
    BorrowResponseDTO getBorrowById(Long id);
    BorrowResponseDTO createBorrow(BorrowRequestDTO request);
    BorrowResponseDTO returnBook(Long id);
    BorrowResponseDTO renewBorrow(Long id);
}
