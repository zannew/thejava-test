package me.zannew.thejavatest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

	// Reflection 관련 : https://www.inflearn.com/course/the-java-code-manipulation 참고
	@Test
	@DisplayName("스터디 생성하기")
	@EnabledOnOs({OS.MAC, OS.LINUX})
	void create_study() {

		String test_env = System.getenv("TEST_ENV");
		System.out.println(test_env);
		assumeTrue("LOCAL".equalsIgnoreCase(test_env));

		assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
			Study actual = new Study(10);
			assertThat(actual.getLimit()).isGreaterThan(0);
		});

		assumingThat("ywchang".equalsIgnoreCase(test_env), () -> {
			Study actual = new Study(-10);
			assertThat(actual.getLimit()).isGreaterThan(0);
		});
	}

	@Test
	@DisplayName("운영체제에 따라 테스트를 비활성화한다.")
	@DisabledOnOs(OS.MAC)
	void disabled_on_os_test() {
		System.out.println("TEST DISABLED.. BY OS");
	}

	@Test
	@DisplayName("JRE 버전에 따라 테스트를 활성화한다.")
	@EnabledOnJre(JRE.JAVA_11)
	void enabled_on_JRE_version() {
		System.out.println("TEST ENABLED.. BY JRE VERSION");
	}

	@Test
	@DisplayName("JRE 버전에 따라 테스트를 바활성화한다.")
	@DisabledOnJre(JRE.JAVA_11)
	void disabled_on_JRE_version() {
		System.out.println("TEST DISABLED.. BY JRE VERSION");
	}

	@Test
	@DisplayName("SYSTEM PROPERTY 값에 따라 테스트를 활성화한다.")
	@EnabledIfSystemProperty(named = "CASE_SENSITIVE", matches = "true")
	void enabled_if_system_property() {
		System.out.println("TEST ENABLED.. BY SYSTEM PROPERTY");
	}

	@Test
	@DisplayName("SYSTEM PROPERTY 값에 따라 테스트를 비활성화한다.")
	@DisabledIfSystemProperty(named = "CASE_SENSITIVE", matches = "false")
	void disabled_if_system_property() {
		System.out.println("TEST DISABLED.. BY SYSTEM PROPERTY");
	}

	@Test
	@DisplayName("테스트 환경 변수 값에 따라 테스트를 활성화한다.")
	@EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "LOCAL")
	void enabled_if_system_env() {
		System.out.println("TEST ENABLED.. BY ENV VALUE");
	}

	@Test
	@DisplayName("테스트 환경 변수 값에 따라 테스트를 비활성화한다.")
	@DisabledIfEnvironmentVariable(named = "TEST_ENV", matches = "LOCAL")
	void disabled_if_system_env() {
		System.out.println("TEST DISABLED.. BY ENV VALUE");
	}

	@BeforeAll
	static void beforeAll() {
		System.out.println("before all");
	}

	@AfterAll
	static void afterAll() {
		System.out.println("after all");
	}

	@BeforeEach
	void beforeEach() {
		System.out.println("before each");
	}

	@AfterEach
	void afterEach() {
		System.out.println("after each");
	}

}