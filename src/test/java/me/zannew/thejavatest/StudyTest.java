package me.zannew.thejavatest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudyTest {

	private int value = 1;

	// Reflection 관련 : https://www.inflearn.com/course/the-java-code-manipulation 참고
	@Test
	@DisplayName("스터디 생성 fast")
	@FastTest
	void create_study() {
		System.out.println(value++);
		Study actual = new Study(10);
		assertThat(actual.getLimit()).isGreaterThan(0);
	}

	@Test
	@DisplayName("스터디 생성 slow")
	@SlowTest
	void create_study_slow() {
		System.out.println(value++);
		System.out.println("create study slow");
	}

	@DisplayName("반복해서 테스트하기")
	@RepeatedTest(value = 10, name = "{displayName}, {currentRepetition} / {totalRepetitions}")
	void repeatTest(RepetitionInfo repetitionInfo) {
		System.out.println(
			"repeat test! " + repetitionInfo.getCurrentRepetition() + " / " + repetitionInfo.getTotalRepetitions());
	}

	@DisplayName("파라미터라이즈드 테스트하기")
	@ParameterizedTest(name = "{index} - {displayName} message={0}")
	@CsvSource({"10, '자바 스터디'", "20, '파이썬 스터디'"})
	void parameterizedTest(@AggregateWith(StudyAggregator.class) Study study) {
		System.out.println(value++);
		System.out.println(study);
	}

	static class StudyAggregator implements ArgumentsAggregator {
		@Override
		public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws
			ArgumentsAggregationException {
			return new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
		}
	}

	static class StudyConverter extends SimpleArgumentConverter {
		@Override
		protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
			assertEquals(Study.class, targetType, "Can only convert to Study");
			return new Study(Integer.parseInt(source.toString()));
		}
	}

	@BeforeAll
	void beforeAll() {
		System.out.println("before all");
	}

	@AfterAll
	void afterAll() {
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