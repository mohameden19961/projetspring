package mr.supnum.library_app.service;

import mr.supnum.library_app.dto.request.NationalityRequestDTO;
import mr.supnum.library_app.dto.response.NationalityResponseDTO;

import java.util.List;

public interface NationalityService {
    List<NationalityResponseDTO> getAllNationalities();
    NationalityResponseDTO getNationalityByCode(String code);
    NationalityResponseDTO createNationality(NationalityRequestDTO request);
    NationalityResponseDTO updateNationality(String code, NationalityRequestDTO request);
    void deleteNationality(String code);
}
