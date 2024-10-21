package org.prj.user.web.v2.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.prj.user.web.v2.dto.MemberDTO;
import org.prj.user.web.v2.entity.Member;
import org.prj.user.web.v2.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import static java.util.Optional.*;
import static java.util.UUID.randomUUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Optional<Long> createMember(MemberDTO memberDTO) {
        Member member = Member.builder()
                .name(memberDTO.getName())
                .email(memberDTO.getEmail())
                .encryptedPwd(passwordEncoder.encode(memberDTO.getPwd()))
                .memberId(randomUUID().toString())
                .build();
        Member savedMember = memberRepository.save(member);
        return savedMember != null ? of(savedMember.getId()) : empty();
    }

    @Override
    public Optional<Member> findByMemberId(Long memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public Iterable<Member> findAllMembers() {
        return memberRepository.findAll();
    }

}
