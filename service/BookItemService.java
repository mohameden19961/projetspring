package mr.supnum.library_app.service;

import mr.supnum.library_app.dto.request.BookItemRequestDTO;
import mr.supnum.library_app.dto.response.BookItemResponseDTO;

import java.util.List;

public interface BookItemService {
    List<BookItemResponseDTO> getAllBookItems();
    BookItemResponseDTO getBookItemById(Long id);
    BookItemResponseDTO createBookItem(BookItemRequestDTO request);
    BookItemResponseDTO updateBookItem(Long id, BookItemRequestDTO request);
    void deleteBookItem(Long id);
}
