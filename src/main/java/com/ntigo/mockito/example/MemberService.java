package com.ntigo.mockito.example;

import java.util.Optional;

public interface MemberService {
    Optional<Member> findById( Long memberId );

    void validate( long id );
}
