package mr.supnum.library_app.service.impl;

import lombok.RequiredArgsConstructor;
import mr.supnum.library_app.dto.request.BookRequestDTO;
import mr.supnum.library_app.dto.response.BookResponseDTO;
import mr.supnum.library_app.entity.Book;
import mr.supnum.library_app.entity.Category;
import mr.supnum.library_app.entity.Language;
import mr.supnum.library_app.entity.Publisher;
import mr.supnum.library_app.exception.ResourceNotFoundException;
import mr.supnum.library_app.repository.BookRepository;
import mr.supnum.library_app.repository.CategoryRepository;
import mr.supnum.library_app.repository.LanguageRepository;
import mr.supnum.library_app.repository.PublisherRepository;
import mr.supnum.library_app.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final LanguageRepository languageRepository;
    private final CategoryRepository categoryRepository;
    private final PublisherRepository publisherRepository;

    @Override
    public List<BookResponseDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BookResponseDTO getBookById(Long id) {
        return bookRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    @Override
    @Transactional
    public BookResponseDTO createBook(BookRequestDTO request) {
        Book book = new Book();
        updateBookFields(book, request);
        return mapToResponse(bookRepository.save(book));
    }

    @Override
    @Transactional
    public BookResponseDTO updateBook(Long id, BookRequestDTO request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        updateBookFields(book, request);
        return mapToResponse(bookRepository.save(book));
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    private void updateBookFields(Book book, BookRequestDTO request) {
        book.setTitle(request.getTitle());
        book.setIsbn(request.getIsbn());
        book.setPublicationYear(request.getPublicationYear());
        book.setEditionNumber(request.getEditionNumber());
        book.setSummary(request.getSummary());

        if (request.getLanguageCode() != null) {
            Language language = languageRepository.findById(request.getLanguageCode())
                    .orElseThrow(() -> new ResourceNotFoundException("Language not found with code: " + request.getLanguageCode()));
            book.setLanguage(language);
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + request.getCategoryId()));
        book.setCategory(category);

        Publisher publisher = publisherRepository.findById(request.getPublisherId())
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found with id: " + request.getPublisherId()));
        book.setPublisher(publisher);
    }

    private BookResponseDTO mapToResponse(Book book) {
        BookResponseDTO response = new BookResponseDTO();
        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setIsbn(book.getIsbn());
        response.setPublicationYear(book.getPublicationYear());
        if (book.getLanguage() != null) {
            response.setLanguageCode(book.getLanguage().getCode());
            response.setLanguageName(book.getLanguage().getName());
        }
        response.setEditionNumber(book.getEditionNumber());
        response.setSummary(book.getSummary());
        response.setCategoryId(book.getCategory().getId());
        response.setCategoryName(book.getCategory().getName());
        response.setPublisherId(book.getPublisher().getId());
        response.setPublisherName(book.getPublisher().getName());
        response.setCreatedAt(book.getCreatedAt());
        response.setUpdatedAt(book.getUpdatedAt());
        return response;
    }
}
