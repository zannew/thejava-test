package me.zannew.thejavatest.member;

import org.springframework.data.jpa.repository.JpaRepository;

import me.zannew.thejavatest.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
