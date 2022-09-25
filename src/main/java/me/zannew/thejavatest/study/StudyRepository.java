package me.zannew.thejavatest.study;

import org.springframework.data.jpa.repository.JpaRepository;

import me.zannew.thejavatest.domain.Study;

public interface StudyRepository extends JpaRepository<Study, Long> {
}
