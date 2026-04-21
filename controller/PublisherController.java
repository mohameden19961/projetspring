package mr.supnum.library_app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mr.supnum.library_app.dto.request.PublisherRequestDTO;
import mr.supnum.library_app.dto.response.PublisherResponseDTO;
import mr.supnum.library_app.service.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @GetMapping
    public List<PublisherResponseDTO> getAllPublishers() {
        return publisherService.getAllPublishers();
    }

    @GetMapping("/{id}")
    public PublisherResponseDTO getPublisher(@PathVariable Long id) {
        return publisherService.getPublisherById(id);
    }

    @PostMapping
    public ResponseEntity<PublisherResponseDTO> createPublisher(@Valid @RequestBody PublisherRequestDTO request) {
        return new ResponseEntity<>(publisherService.createPublisher(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public PublisherResponseDTO updatePublisher(@PathVariable Long id, @Valid @RequestBody PublisherRequestDTO request) {
        return publisherService.updatePublisher(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
    }
}
