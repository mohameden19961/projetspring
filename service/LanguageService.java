package mr.supnum.library_app.service;

import mr.supnum.library_app.dto.request.LanguageRequestDTO;
import mr.supnum.library_app.dto.response.LanguageResponseDTO;

import java.util.List;

public interface LanguageService {
    List<LanguageResponseDTO> getAllLanguages();
    LanguageResponseDTO getLanguageByCode(String code);
    LanguageResponseDTO createLanguage(LanguageRequestDTO request);
    LanguageResponseDTO updateLanguage(String code, LanguageRequestDTO request);
    void deleteLanguage(String code);
}
