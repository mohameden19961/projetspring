package mr.supnum.library_app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mr.supnum.library_app.dto.request.NationalityRequestDTO;
import mr.supnum.library_app.dto.response.NationalityResponseDTO;
import mr.supnum.library_app.service.NationalityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nationalities")
@RequiredArgsConstructor
public class NationalityController {

    private final NationalityService nationalityService;

    @GetMapping
    public List<NationalityResponseDTO> getAllNationalities() {
        return nationalityService.getAllNationalities();
    }

    @GetMapping("/{code}")
    public NationalityResponseDTO getNationality(@PathVariable String code) {
        return nationalityService.getNationalityByCode(code);
    }

    @PostMapping
    public ResponseEntity<NationalityResponseDTO> createNationality(@Valid @RequestBody NationalityRequestDTO request) {
        return new ResponseEntity<>(nationalityService.createNationality(request), HttpStatus.CREATED);
    }

    @PutMapping("/{code}")
    public NationalityResponseDTO updateNationality(@PathVariable String code, @Valid @RequestBody NationalityRequestDTO request) {
        return nationalityService.updateNationality(code, request);
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNationality(@PathVariable String code) {
        nationalityService.deleteNationality(code);
    }
}
