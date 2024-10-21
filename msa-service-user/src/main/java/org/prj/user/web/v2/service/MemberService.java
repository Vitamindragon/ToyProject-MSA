package org.prj.user.web.v2.service;

import org.prj.user.web.v2.dto.MemberDTO;
import org.prj.user.web.v2.entity.Member;

import java.util.Optional;

public interface MemberService {

    Optional<Long> createMember(MemberDTO reqMemberDTO);

    Optional<Member> findByMemberId(Long memberId);

    Iterable<Member> findAllMembers();
}
