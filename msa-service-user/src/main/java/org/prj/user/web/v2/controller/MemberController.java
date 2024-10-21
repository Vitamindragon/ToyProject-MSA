package org.prj.user.web.v2.controller;

import lombok.RequiredArgsConstructor;
import org.prj.user.web.v2.dto.MemberDTO;
import org.prj.user.web.v2.entity.Member;
import org.prj.user.web.v2.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.prj.user.web.v2.dto.MemberDTO.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<MemberDTO>> getAllMembers() {
        Iterable<Member> memberList = memberService.findAllMembers();
        List<MemberDTO> result = new ArrayList<>();
        memberList.forEach(i ->
                result.add(createMemberDTO(i.getEmail(), i.getName(), null)));

        return status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<Member> getMember(@PathVariable Long memberId) {
        Member findMember = memberService.findByMemberId(memberId).get();
        return status(HttpStatus.OK).body(findMember);
    }


    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody MemberDTO memberDTO) {
        Long memberId = memberService.createMember(memberDTO).get();
        Optional<Member> findMember = memberService.findByMemberId(memberId);
        return status(CREATED).body(findMember.get());
    }

}
