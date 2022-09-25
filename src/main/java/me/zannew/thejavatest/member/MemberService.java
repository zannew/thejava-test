package me.zannew.thejavatest.member;

import java.util.Optional;

import me.zannew.thejavatest.domain.Member;

public interface MemberService {

	Optional<Member> findById(Long memberId);

	void validate(Long memberId);
}
