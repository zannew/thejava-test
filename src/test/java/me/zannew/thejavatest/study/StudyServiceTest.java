package me.zannew.thejavatest.study;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import me.zannew.thejavatest.member.MemberService;

class StudyServiceTest {

	@DisplayName("mock 객체를 인자로 사용하여 StudyService 인스턴스를 성공적으로 생성한다.")
	@Test
	void createStudyService() {

		MemberService memberService = mock(MemberService.class);
		StudyRepository studyRepository = mock(StudyRepository.class);
		StudyService studyService = new StudyService(memberService, studyRepository);
		assertNotNull(studyService);
	}
}