package mr.supnum.library_app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mr.supnum.library_app.dto.request.MemberRequestDTO;
import mr.supnum.library_app.dto.response.MemberResponseDTO;
import mr.supnum.library_app.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public List<MemberResponseDTO> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public MemberResponseDTO getMember(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @PostMapping
    public ResponseEntity<MemberResponseDTO> createMember(@Valid @RequestBody MemberRequestDTO request) {
        return new ResponseEntity<>(memberService.createMember(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public MemberResponseDTO updateMember(@PathVariable Long id, @Valid @RequestBody MemberRequestDTO request) {
        return memberService.updateMember(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }
}
