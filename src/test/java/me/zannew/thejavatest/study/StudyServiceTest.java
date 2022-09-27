package me.zannew.thejavatest.study;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import me.zannew.thejavatest.domain.Member;
import me.zannew.thejavatest.domain.Study;
import me.zannew.thejavatest.member.MemberService;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

	@Mock
	MemberService memberService;
	@Mock
	StudyRepository studyRepository;

	@DisplayName("mock 객체를 인자로 사용하여 StudyService 인스턴스를 성공적으로 생성한다.")
	@Test
	void createStudyService() {
		MemberService memberService = mock(MemberService.class);
		StudyRepository studyRepository = mock(StudyRepository.class);
		StudyService studyService = new StudyService(memberService, studyRepository);
		assertNotNull(studyService);

		assertThat(memberService).isInstanceOf(MemberService.class);
		assertThat(studyRepository).isInstanceOf(StudyRepository.class);
		assertThat(studyService).isInstanceOf(StudyService.class);
	}

	@DisplayName("어노테이션 사용하여 mock 객체 생성하여 StudyService 인스턴스를 성공적으로 생성한다.")
	@Test
	void createStudyServiceWithAnnotation() {
		StudyService studyService = new StudyService(memberService, studyRepository);
		assertNotNull(studyService);
	}

	@DisplayName("어노테이션을 테스트 메서드 파라미터로 직접 사용할 수 있다.")
	@Test
	void createStudyServiceWithAnnotation2(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {
		StudyService studyService = new StudyService(memberService, studyRepository);
		assertNotNull(studyService);
	}

	@Test
	void beforeStubbingTest() {
		Optional<Member> member = memberService.findById(1L);
		memberService.validate(2L);
	}

	@DisplayName("특정 파라미터인 경우 원하는 member 객체를 리턴한다.")
	@Test
	void stubbingMember() {

		StudyService studyService = new StudyService(memberService, studyRepository);
		assertNotNull(studyService);

		Member member = new Member();
		member.setId(1L);
		member.setEmail("woni@woni.com");

		when(memberService.findById(1L)).thenReturn(Optional.of(member));

		assertEquals("woni@woni.com", memberService.findById(1L).get().getEmail());
	}

	@DisplayName("어떤 파라미터를 전달해도 원하는 member 객체를 리턴한다.")
	@Test
	void stubbingMemberWithAny() {

		StudyService studyService = new StudyService(memberService, studyRepository);
		assertNotNull(studyService);

		Member member = new Member();
		member.setId(1L);
		member.setEmail("woni@woni.com");

		when(memberService.findById(any())).thenReturn(Optional.of(member));

		assertEquals("woni@woni.com", memberService.findById(1L).get().getEmail());
		assertEquals("woni@woni.com", memberService.findById(2L).get().getEmail());
		assertEquals("woni@woni.com", memberService.findById(3L).get().getEmail());
	}

	@DisplayName("void 타입 특정 파라미터를 전달하는 경우만 예외를 던진다.")
	@Test
	void doThrowWhenVoidType() {
		doThrow(new IllegalArgumentException()).when(memberService).validate(1L);

		assertThrows(IllegalArgumentException.class, () -> {
			memberService.validate(1L);
		});

		memberService.validate(2L);
	}

	@DisplayName("호출 순서에 따라 다른 결과를 발생시킨다.")
	@Test
	void differentResultByCallOrder() {

		StudyService studyService = new StudyService(memberService, studyRepository);
		assertNotNull(studyService);

		Member member = new Member();
		member.setId(1L);
		member.setEmail("woni@woni.com");

		when(memberService.findById(any()))
			.thenReturn(Optional.of(member))
			.thenThrow(new IllegalArgumentException())
			.thenReturn(Optional.empty());

		assertEquals("woni@woni.com", memberService.findById(1L).get().getEmail());
		assertThrows(IllegalArgumentException.class, () -> {
			memberService.findById(2L);
		});
		assertEquals(Optional.empty(), memberService.findById(3L));
	}

	@DisplayName("Stubbing 연습 문제")
	@Test
	void exerciseStubbing() {

		StudyService studyService = new StudyService(memberService, studyRepository);
		assertNotNull(studyService);

		Member member = new Member();
		member.setId(1L);
		member.setEmail("woni@woni.com");

		Study study = new Study(10, "테스트");

		// TODO memberService 객체에 findById 메서드를 1L 값으로 호출하면 member 객체를 리턴하도록 stubbing
		when(memberService.findById(1L)).thenReturn(Optional.of(member));
		// TODO studyRepository 객체에 save 메서드를 study 객체로 호출하면 study 객체 그래도 리턴하도록 stubbing
		when(studyRepository.save(study)).thenReturn(study);

		studyService.createNewStudy(1L, study);

		assertNotNull(study.getOwner());
		assertEquals(member, study.getOwner());

	}
}