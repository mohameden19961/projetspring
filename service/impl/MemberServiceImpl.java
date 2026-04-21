package mr.supnum.library_app.service.impl;

import lombok.RequiredArgsConstructor;
import mr.supnum.library_app.dto.request.MemberRequestDTO;
import mr.supnum.library_app.dto.response.MemberResponseDTO;
import mr.supnum.library_app.entity.Member;
import mr.supnum.library_app.exception.ResourceNotFoundException;
import mr.supnum.library_app.repository.MemberRepository;
import mr.supnum.library_app.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public List<MemberResponseDTO> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MemberResponseDTO getMemberById(Long id) {
        return memberRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id));
    }

    @Override
    @Transactional
    public MemberResponseDTO createMember(MemberRequestDTO request) {
        Member member = new Member();
        updateMemberFields(member, request);
        member.setMemberSince(LocalDateTime.now());
        return mapToResponse(memberRepository.save(member));
    }

    @Override
    @Transactional
    public MemberResponseDTO updateMember(Long id, MemberRequestDTO request) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id));
        updateMemberFields(member, request);
        return mapToResponse(memberRepository.save(member));
    }

    @Override
    @Transactional
    public void deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new ResourceNotFoundException("Member not found with id: " + id);
        }
        memberRepository.deleteById(id);
    }

    private void updateMemberFields(Member member, MemberRequestDTO request) {
        member.setFirstName(request.getFirstName());
        member.setLastName(request.getLastName());
        member.setEmail(request.getEmail());
        member.setPhone(request.getPhone());
        member.setMemberType(request.getMemberType());
        member.setStatus(request.getStatus());
        member.setMaxBorrows(request.getMaxBorrows());
    }

    private MemberResponseDTO mapToResponse(Member member) {
        MemberResponseDTO response = new MemberResponseDTO();
        response.setId(member.getId());
        response.setFirstName(member.getFirstName());
        response.setLastName(member.getLastName());
        response.setEmail(member.getEmail());
        response.setPhone(member.getPhone());
        response.setMemberType(member.getMemberType());
        response.setStatus(member.getStatus());
        response.setMaxBorrows(member.getMaxBorrows());
        response.setMemberSince(member.getMemberSince());
        response.setCreatedAt(member.getCreatedAt());
        response.setUpdatedAt(member.getUpdatedAt());
        return response;
    }
}
