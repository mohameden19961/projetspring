package mr.supnum.library_app.service.impl;

import lombok.RequiredArgsConstructor;
import mr.supnum.library_app.dto.request.BookItemRequestDTO;
import mr.supnum.library_app.dto.response.BookItemResponseDTO;
import mr.supnum.library_app.entity.Book;
import mr.supnum.library_app.entity.BookItem;
import mr.supnum.library_app.exception.ResourceNotFoundException;
import mr.supnum.library_app.repository.BookItemRepository;
import mr.supnum.library_app.repository.BookRepository;
import mr.supnum.library_app.service.BookItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookItemServiceImpl implements BookItemService {

    private final BookItemRepository bookItemRepository;
    private final BookRepository bookRepository;

    @Override
    public List<BookItemResponseDTO> getAllBookItems() {
        return bookItemRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BookItemResponseDTO getBookItemById(Long id) {
        return bookItemRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("BookItem not found with id: " + id));
    }

    @Override
    @Transactional
    public BookItemResponseDTO createBookItem(BookItemRequestDTO request) {
        BookItem bookItem = new BookItem();
        updateBookItemFields(bookItem, request);
        return mapToResponse(bookItemRepository.save(bookItem));
    }

    @Override
    @Transactional
    public BookItemResponseDTO updateBookItem(Long id, BookItemRequestDTO request) {
        BookItem bookItem = bookItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BookItem not found with id: " + id));
        updateBookItemFields(bookItem, request);
        return mapToResponse(bookItemRepository.save(bookItem));
    }

    @Override
    @Transactional
    public void deleteBookItem(Long id) {
        if (!bookItemRepository.existsById(id)) {
            throw new ResourceNotFoundException("BookItem not found with id: " + id);
        }
        bookItemRepository.deleteById(id);
    }

    private void updateBookItemFields(BookItem bookItem, BookItemRequestDTO request) {
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + request.getBookId()));
        bookItem.setBook(book);
        bookItem.setBarcode(request.getBarcode());
        bookItem.setStatus(request.getStatus());
        bookItem.setAcquiredAt(request.getAcquiredAt());
        bookItem.setPrice(request.getPrice());
        bookItem.setNotes(request.getNotes());
    }

    private BookItemResponseDTO mapToResponse(BookItem bookItem) {
        BookItemResponseDTO response = new BookItemResponseDTO();
        response.setId(bookItem.getId());
        response.setBookId(bookItem.getBook().getId());
        response.setBookTitle(bookItem.getBook().getTitle());
        response.setBarcode(bookItem.getBarcode());
        response.setStatus(bookItem.getStatus());
        response.setAcquiredAt(bookItem.getAcquiredAt());
        response.setPrice(bookItem.getPrice());
        response.setNotes(bookItem.getNotes());
        response.setVersion(bookItem.getVersion());
        response.setCreatedAt(bookItem.getCreatedAt());
        response.setUpdatedAt(bookItem.getUpdatedAt());
        return response;
    }
}
