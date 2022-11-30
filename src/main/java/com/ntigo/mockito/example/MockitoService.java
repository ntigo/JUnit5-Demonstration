package com.ntigo.mockito.example;

public class MockitoService {

    private final MemberService memberService;
    private final MockRepository mockRepository;

    public MockitoService( MemberService memberService, MockRepository mockRepository ) {
        assert memberService != null : "memberService is not null";
        assert mockRepository != null : "mockRepository is not null";

        this.memberService = memberService;
        this.mockRepository = mockRepository;
    }

    public Mock createNewMock( Long memberId, Mock mock ) {
//        Optional<Member> member = memberService.
        return null;
    }
}
