package mr.supnum.library_app.service.impl;

import lombok.RequiredArgsConstructor;
import mr.supnum.library_app.dto.request.AuthorRequestDTO;
import mr.supnum.library_app.dto.response.AuthorResponseDTO;
import mr.supnum.library_app.entity.Author;
import mr.supnum.library_app.entity.Nationality;
import mr.supnum.library_app.exception.ResourceNotFoundException;
import mr.supnum.library_app.repository.AuthorRepository;
import mr.supnum.library_app.repository.NationalityRepository;
import mr.supnum.library_app.service.AuthorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final NationalityRepository nationalityRepository;

    @Override
    public List<AuthorResponseDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorResponseDTO getAuthorById(Long id) {
        return authorRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
    }

    @Override
    @Transactional
    public AuthorResponseDTO createAuthor(AuthorRequestDTO request) {
        Author author = new Author();
        author.setName(request.getName());
        author.setBio(request.getBio());
        
        if (request.getNationalityCode() != null) {
            Nationality nationality = nationalityRepository.findById(request.getNationalityCode())
                    .orElseThrow(() -> new ResourceNotFoundException("Nationality not found with code: " + request.getNationalityCode()));
            author.setNationality(nationality);
        }
        
        return mapToResponse(authorRepository.save(author));
    }

    @Override
    @Transactional
    public AuthorResponseDTO updateAuthor(Long id, AuthorRequestDTO request) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
        author.setName(request.getName());
        author.setBio(request.getBio());
        
        if (request.getNationalityCode() != null) {
            Nationality nationality = nationalityRepository.findById(request.getNationalityCode())
                    .orElseThrow(() -> new ResourceNotFoundException("Nationality not found with code: " + request.getNationalityCode()));
            author.setNationality(nationality);
        } else {
            author.setNationality(null);
        }
        
        return mapToResponse(authorRepository.save(author));
    }

    @Override
    @Transactional
    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }

    private AuthorResponseDTO mapToResponse(Author author) {
        AuthorResponseDTO response = new AuthorResponseDTO();
        response.setId(author.getId());
        response.setName(author.getName());
        response.setBio(author.getBio());
        if (author.getNationality() != null) {
            response.setNationalityCode(author.getNationality().getCode());
            response.setNationalityName(author.getNationality().getName());
        }
        response.setCreatedAt(author.getCreatedAt());
        response.setUpdatedAt(author.getUpdatedAt());
        return response;
    }
}
