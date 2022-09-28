package me.zannew.thejavatest.member;

import java.util.Optional;

import me.zannew.thejavatest.domain.Member;
import me.zannew.thejavatest.domain.Study;

public interface MemberService {

	Optional<Member> findById(Long memberId);

	void validate(Long memberId);

	void notify(Study newStudy);

	void notify(Member member);
}
