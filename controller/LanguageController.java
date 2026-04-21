package mr.supnum.library_app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mr.supnum.library_app.dto.request.LanguageRequestDTO;
import mr.supnum.library_app.dto.response.LanguageResponseDTO;
import mr.supnum.library_app.service.LanguageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService languageService;

    @GetMapping
    public List<LanguageResponseDTO> getAllLanguages() {
        return languageService.getAllLanguages();
    }

    @GetMapping("/{code}")
    public LanguageResponseDTO getLanguage(@PathVariable String code) {
        return languageService.getLanguageByCode(code);
    }

    @PostMapping
    public ResponseEntity<LanguageResponseDTO> createLanguage(@Valid @RequestBody LanguageRequestDTO request) {
        return new ResponseEntity<>(languageService.createLanguage(request), HttpStatus.CREATED);
    }

    @PutMapping("/{code}")
    public LanguageResponseDTO updateLanguage(@PathVariable String code, @Valid @RequestBody LanguageRequestDTO request) {
        return languageService.updateLanguage(code, request);
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLanguage(@PathVariable String code) {
        languageService.deleteLanguage(code);
    }
}
