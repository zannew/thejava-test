package me.zannew.thejavatest.domain;

import lombok.Getter;
import lombok.Setter;
import me.zannew.thejavatest.StudyStatus;

@Setter
@Getter
public class Study {

	private StudyStatus status;
	private int limit;
	private String name;
	private Member owner;

	public Study(int limit, String name) {
		this.limit = limit;
		this.name = name;
	}

	public Study(int limit) {
		if (limit < 0) {
			throw new IllegalArgumentException("limit 은 0보다 커야 한다.");
		}
		this.limit = limit;
	}

	public StudyStatus getStatus() {
		return this.status;
	}

	public int getLimit() {
		return this.limit;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Study{" +
			"status=" + status +
			", limit=" + limit +
			", name='" + name + '\'' +
			", owner=" + owner +
			'}';
	}
}
