package mr.supnum.library_app.service;

import mr.supnum.library_app.dto.request.MemberRequestDTO;
import mr.supnum.library_app.dto.response.MemberResponseDTO;

import java.util.List;

public interface MemberService {
    List<MemberResponseDTO> getAllMembers();
    MemberResponseDTO getMemberById(Long id);
    MemberResponseDTO createMember(MemberRequestDTO request);
    MemberResponseDTO updateMember(Long id, MemberRequestDTO request);
    void deleteMember(Long id);
}
