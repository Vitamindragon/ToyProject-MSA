package org.prj.user.web.v2.repository;

import org.prj.user.web.v2.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
