package mr.supnum.library_app.service;

import mr.supnum.library_app.dto.request.AuthorRequestDTO;
import mr.supnum.library_app.dto.response.AuthorResponseDTO;

import java.util.List;

public interface AuthorService {
    List<AuthorResponseDTO> getAllAuthors();
    AuthorResponseDTO getAuthorById(Long id);
    AuthorResponseDTO createAuthor(AuthorRequestDTO request);
    AuthorResponseDTO updateAuthor(Long id, AuthorRequestDTO request);
    void deleteAuthor(Long id);
}
