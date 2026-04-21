package mr.supnum.library_app.service.impl;

import lombok.RequiredArgsConstructor;
import mr.supnum.library_app.dto.request.PublisherRequestDTO;
import mr.supnum.library_app.dto.response.PublisherResponseDTO;
import mr.supnum.library_app.entity.Publisher;
import mr.supnum.library_app.exception.ResourceNotFoundException;
import mr.supnum.library_app.repository.PublisherRepository;
import mr.supnum.library_app.service.PublisherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    @Override
    public List<PublisherResponseDTO> getAllPublishers() {
        return publisherRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PublisherResponseDTO getPublisherById(Long id) {
        return publisherRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found with id: " + id));
    }

    @Override
    @Transactional
    public PublisherResponseDTO createPublisher(PublisherRequestDTO request) {
        Publisher publisher = new Publisher();
        publisher.setName(request.getName());
        publisher.setAddress(request.getAddress());
        publisher.setEmail(request.getEmail());
        publisher.setWebsite(request.getWebsite());
        return mapToResponse(publisherRepository.save(publisher));
    }

    @Override
    @Transactional
    public PublisherResponseDTO updatePublisher(Long id, PublisherRequestDTO request) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found with id: " + id));
        publisher.setName(request.getName());
        publisher.setAddress(request.getAddress());
        publisher.setEmail(request.getEmail());
        publisher.setWebsite(request.getWebsite());
        return mapToResponse(publisherRepository.save(publisher));
    }

    @Override
    @Transactional
    public void deletePublisher(Long id) {
        if (!publisherRepository.existsById(id)) {
            throw new ResourceNotFoundException("Publisher not found with id: " + id);
        }
        publisherRepository.deleteById(id);
    }

    private PublisherResponseDTO mapToResponse(Publisher publisher) {
        PublisherResponseDTO response = new PublisherResponseDTO();
        response.setId(publisher.getId());
        response.setName(publisher.getName());
        response.setAddress(publisher.getAddress());
        response.setEmail(publisher.getEmail());
        response.setWebsite(publisher.getWebsite());
        response.setCreatedAt(publisher.getCreatedAt());
        response.setUpdatedAt(publisher.getUpdatedAt());
        return response;
    }
}
