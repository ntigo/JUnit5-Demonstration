package com.ntigo.mockito.demo;

import com.ntigo.mockito.example.Member;
import com.ntigo.mockito.example.MemberService;
import com.ntigo.mockito.example.MockRepository;
import com.ntigo.mockito.example.MockitoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MockitoTest {

    @Test
    @DisplayName("MockExtension Method 주입하여 Stubbing 하기")
    void createMockServiceForMockMethodAnnotation1( @Mock MemberService _memberService,
                                                    @Mock MockRepository _mockRepository ) {
        Member member = new Member();
        member.setId( 1L );
        member.setEmail( "dev.ntigo@gmail.com" );

        // 어떤 값이 들어오더라도 member 를 반환
        when( _memberService.findById( any() ) ).thenReturn( Optional.of( member ) );

        // validate 1이라는 값을 대입할 경우 Exception 발생
        doThrow( new IllegalArgumentException() ).when( _memberService ).validate( 1 );

        // 순서에 따라 다르게 반환하기 / 1) member 2) exception 3) empty
        when( _memberService.findById( any() ) )
                .thenReturn( Optional.of( member ) )
                .thenThrow( new IllegalArgumentException() )
                .thenReturn( Optional.empty() );

        MockitoService mockitoService = new MockitoService( _memberService, _mockRepository );
        assertNotNull( mockitoService, () -> "mockitoService is null." );
    }
}
