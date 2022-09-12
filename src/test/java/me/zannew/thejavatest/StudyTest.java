package me.zannew.thejavatest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    // Reflection 관련 : https://www.inflearn.com/course/the-java-code-manipulation 참고
    @Test
    @DisplayName("스터디 생성하기 1 \uD83D \uDE31")
    void create_1() {
        Study study = new Study();
        assertNotNull(study);
        System.out.println("create1");
    }

    @Test
    @DisplayName("스터디 생성하기 2")
    void create_2() {
        Study study = new Study();
        assertNotNull(study);
        System.out.println("create2");
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