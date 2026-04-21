package mr.supnum.library_app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mr.supnum.library_app.dto.request.BorrowRequestDTO;
import mr.supnum.library_app.dto.response.BorrowResponseDTO;
import mr.supnum.library_app.service.BorrowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrows")
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowService borrowService;

    @GetMapping
    public List<BorrowResponseDTO> getAllBorrows() {
        return borrowService.getAllBorrows();
    }

    @GetMapping("/{id}")
    public BorrowResponseDTO getBorrow(@PathVariable Long id) {
        return borrowService.getBorrowById(id);
    }

    @PostMapping
    public ResponseEntity<BorrowResponseDTO> createBorrow(@Valid @RequestBody BorrowRequestDTO request) {
        return new ResponseEntity<>(borrowService.createBorrow(request), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/return")
    public BorrowResponseDTO returnBook(@PathVariable Long id) {
        return borrowService.returnBook(id);
    }

    @PatchMapping("/{id}/renew")
    public BorrowResponseDTO renewBorrow(@PathVariable Long id) {
        return borrowService.renewBorrow(id);
    }
}
