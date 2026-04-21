package mr.supnum.library_app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mr.supnum.library_app.dto.request.BookItemRequestDTO;
import mr.supnum.library_app.dto.response.BookItemResponseDTO;
import mr.supnum.library_app.service.BookItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book-items")
@RequiredArgsConstructor
public class BookItemController {

    private final BookItemService bookItemService;

    @GetMapping
    public List<BookItemResponseDTO> getAllBookItems() {
        return bookItemService.getAllBookItems();
    }

    @GetMapping("/{id}")
    public BookItemResponseDTO getBookItem(@PathVariable Long id) {
        return bookItemService.getBookItemById(id);
    }

    @PostMapping
    public ResponseEntity<BookItemResponseDTO> createBookItem(@Valid @RequestBody BookItemRequestDTO request) {
        return new ResponseEntity<>(bookItemService.createBookItem(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public BookItemResponseDTO updateBookItem(@PathVariable Long id, @Valid @RequestBody BookItemRequestDTO request) {
        return bookItemService.updateBookItem(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookItem(@PathVariable Long id) {
        bookItemService.deleteBookItem(id);
    }
}
