package me.zannew.thejavatest;

import org.junit.Before;
import org.junit.Test;

public class StudyJUnit4Test {

	@Before
	public void before() {
		System.out.println("before");
	}

	@Test
	public void create_study_1() {
		System.out.println("create_study test 1");
	}

	@Test
	public void create_study_2() {
		System.out.println("create_study test 2");
	}
}
