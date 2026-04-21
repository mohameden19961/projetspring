package mr.supnum.library_app.service;

import mr.supnum.library_app.dto.request.BookRequestDTO;
import mr.supnum.library_app.dto.response.BookResponseDTO;

import java.util.List;

public interface BookService {
    List<BookResponseDTO> getAllBooks();
    BookResponseDTO getBookById(Long id);
    BookResponseDTO createBook(BookRequestDTO request);
    BookResponseDTO updateBook(Long id, BookRequestDTO request);
    void deleteBook(Long id);
}
