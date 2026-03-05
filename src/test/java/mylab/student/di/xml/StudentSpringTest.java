package mylab.student.di.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-student-di.xml")
public class StudentSpringTest {

    // 다이어그램 지시사항: @Autowired와 @Qualifier 혼용 사용
    @Autowired
    @Qualifier("javaCourse")
    private Course course;

    // 다이어그램 지시사항: @Resource 사용
    @Resource(name = "gradeService")
    private GradeService service;

    @Test
    public void testCourse() {
        assertNotNull(course, "Course 객체는 Null이 아니어야 합니다.");
        assertEquals(3, course.getStudents().size(), "강좌에 등록된 학생은 3명이어야 합니다.");
        
        // 평균 점수 검증 (95 + 85 + 75) / 3 = 85.0
        assertEquals(85.0, course.getAverageScore(), "평균 점수가 일치해야 합니다.");
    }

    @Test
    public void testGradeService() {
        assertNotNull(service, "GradeService 객체는 Null이 아니어야 합니다.");
        
        // 1. 고득점자 필터링 테스트 (80점 이상)
        List<Student> highScorers = service.getHighScoreStudents(80);
        assertEquals(2, highScorers.size(), "80점 이상인 학생은 2명이어야 합니다.");
        
        // 2. 학점 계산 로직 검증
        assertEquals("A", service.calculateGrade("S001"), "S001 학생은 A학점이어야 합니다.");
        assertEquals("B", service.calculateGrade("S002"), "S002 학생은 B학점이어야 합니다.");
        assertEquals("C", service.calculateGrade("S003"), "S003 학생은 C학점이어야 합니다.");
    }
}