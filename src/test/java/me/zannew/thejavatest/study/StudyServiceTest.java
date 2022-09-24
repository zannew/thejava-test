package me.zannew.thejavatest.study;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

}