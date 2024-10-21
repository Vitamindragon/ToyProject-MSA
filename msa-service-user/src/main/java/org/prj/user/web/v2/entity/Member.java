package org.prj.user.web.v2.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String email;//이메일

    @Column(nullable = false)
    private String encryptedPwd;//비밀번호

    @Column(nullable = false, length = 50)
    private String name;//이름

    @Column(nullable = false, unique = true)
    private String memberId;

    @Builder
    public Member(String email, String encryptedPwd, String name, String memberId) {
        this.email = email;
        this.encryptedPwd = encryptedPwd;
        this.name = name;
        this.memberId = memberId;
    }
}
