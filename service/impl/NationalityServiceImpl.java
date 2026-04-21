package mr.supnum.library_app.service.impl;

import lombok.RequiredArgsConstructor;
import mr.supnum.library_app.dto.request.NationalityRequestDTO;
import mr.supnum.library_app.dto.response.NationalityResponseDTO;
import mr.supnum.library_app.entity.Nationality;
import mr.supnum.library_app.exception.ResourceNotFoundException;
import mr.supnum.library_app.repository.NationalityRepository;
import mr.supnum.library_app.service.NationalityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NationalityServiceImpl implements NationalityService {

    private final NationalityRepository nationalityRepository;

    @Override
    public List<NationalityResponseDTO> getAllNationalities() {
        return nationalityRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public NationalityResponseDTO getNationalityByCode(String code) {
        return nationalityRepository.findById(code)
                .map(this::mapToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Nationality not found with code: " + code));
    }

    @Override
    @Transactional
    public NationalityResponseDTO createNationality(NationalityRequestDTO request) {
        Nationality nationality = new Nationality();
        nationality.setCode(request.getCode());
        nationality.setName(request.getName());
        return mapToResponse(nationalityRepository.save(nationality));
    }

    @Override
    @Transactional
    public NationalityResponseDTO updateNationality(String code, NationalityRequestDTO request) {
        Nationality nationality = nationalityRepository.findById(code)
                .orElseThrow(() -> new ResourceNotFoundException("Nationality not found with code: " + code));
        nationality.setName(request.getName());
        return mapToResponse(nationalityRepository.save(nationality));
    }

    @Override
    @Transactional
    public void deleteNationality(String code) {
        if (!nationalityRepository.existsById(code)) {
            throw new ResourceNotFoundException("Nationality not found with code: " + code);
        }
        nationalityRepository.deleteById(code);
    }

    private NationalityResponseDTO mapToResponse(Nationality nationality) {
        NationalityResponseDTO response = new NationalityResponseDTO();
        response.setCode(nationality.getCode());
        response.setName(nationality.getName());
        response.setCreatedAt(nationality.getCreatedAt());
        return response;
    }
}
