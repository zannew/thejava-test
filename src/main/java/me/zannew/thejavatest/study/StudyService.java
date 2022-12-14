package me.zannew.thejavatest.study;

import java.util.Optional;

import me.zannew.thejavatest.domain.Member;
import me.zannew.thejavatest.domain.Study;
import me.zannew.thejavatest.member.MemberService;

public class StudyService {

	private final MemberService memberService;
	private final StudyRepository studyRepository;

	public StudyService(MemberService memberService, StudyRepository studyRepository) {
		assert memberService != null;
		assert studyRepository != null;
		this.memberService = memberService;
		this.studyRepository = studyRepository;
	}

	public Study createNewStudy(Long memberId, Study study) {
		Optional<Member> member = memberService.findById(memberId);
		study.setOwner(
			member.orElseThrow(() -> new IllegalArgumentException("Member doesn't exist for id : " + memberId)));
		Study newStudy = studyRepository.save(study);
		memberService.notify(newStudy);
		// memberService.notify(member.get());
		return newStudy;
	}

	public Study openStudy(Study study) {
		study.open();
		Study openedStudy = studyRepository.save(study);
		memberService.notify(openedStudy);
		return openedStudy;
	}

}
