package mr.supnum.library_app.service;

import mr.supnum.library_app.dto.request.PublisherRequestDTO;
import mr.supnum.library_app.dto.response.PublisherResponseDTO;

import java.util.List;

public interface PublisherService {
    List<PublisherResponseDTO> getAllPublishers();
    PublisherResponseDTO getPublisherById(Long id);
    PublisherResponseDTO createPublisher(PublisherRequestDTO request);
    PublisherResponseDTO updatePublisher(Long id, PublisherRequestDTO request);
    void deletePublisher(Long id);
}
