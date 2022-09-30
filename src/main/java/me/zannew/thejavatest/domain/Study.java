package me.zannew.thejavatest.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.zannew.thejavatest.StudyStatus;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Study {

	@GeneratedValue
	@Id
	private Long id;
	private StudyStatus status = StudyStatus.DRAFT;
	private int limitCount;
	private String name;
	private LocalDateTime openedDateTime;
	@ManyToOne
	private Member owner;

	public Study(int limit, String name) {
		this.limitCount = limit;
		this.name = name;
	}

	public Study(int limit) {
		if (limit < 0) {
			throw new IllegalArgumentException("limit 은 0보다 커야 한다.");
		}
		this.limitCount = limit;
	}

	public void open() {
		this.openedDateTime = LocalDateTime.now();
		this.status = StudyStatus.OPENED;
	}
}
