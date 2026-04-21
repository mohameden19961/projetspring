package mr.supnum.library_app.service.impl;

import lombok.RequiredArgsConstructor;
import mr.supnum.library_app.dto.request.LanguageRequestDTO;
import mr.supnum.library_app.dto.response.LanguageResponseDTO;
import mr.supnum.library_app.entity.Language;
import mr.supnum.library_app.exception.ResourceNotFoundException;
import mr.supnum.library_app.repository.LanguageRepository;
import mr.supnum.library_app.service.LanguageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    @Override
    public List<LanguageResponseDTO> getAllLanguages() {
        return languageRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LanguageResponseDTO getLanguageByCode(String code) {
        return languageRepository.findById(code)
                .map(this::mapToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Language not found with code: " + code));
    }

    @Override
    @Transactional
    public LanguageResponseDTO createLanguage(LanguageRequestDTO request) {
        Language language = new Language();
        language.setCode(request.getCode());
        language.setName(request.getName());
        return mapToResponse(languageRepository.save(language));
    }

    @Override
    @Transactional
    public LanguageResponseDTO updateLanguage(String code, LanguageRequestDTO request) {
        Language language = languageRepository.findById(code)
                .orElseThrow(() -> new ResourceNotFoundException("Language not found with code: " + code));
        language.setName(request.getName());
        return mapToResponse(languageRepository.save(language));
    }

    @Override
    @Transactional
    public void deleteLanguage(String code) {
        if (!languageRepository.existsById(code)) {
            throw new ResourceNotFoundException("Language not found with code: " + code);
        }
        languageRepository.deleteById(code);
    }

    private LanguageResponseDTO mapToResponse(Language language) {
        LanguageResponseDTO response = new LanguageResponseDTO();
        response.setCode(language.getCode());
        response.setName(language.getName());
        response.setCreatedAt(language.getCreatedAt());
        return response;
    }
}
