package myspring.di.xml;

import static org.junit.jupiter.api.Assertions.*;  // static으로 선언한 이유, * 로 메서드 레벨까지 내려가서 모든 기능 임포트
// 원래는 Assertions.assertSame(helloById, helloByType);
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloBeanJunitTest {
	
	ApplicationContext context;
	
	@BeforeEach
	void init() {
		//Container 객체 생성 
		// 1. ApplicationContext: 스프링 컨테이너(공장)의 규칙을 담은 인터페이스
		// 2. GenericXmlApplicationContext: XML 파일을 읽어 실제로 객체를 만들어주는 일꾼(구현체)
		// 3. "classpath:hello-bean.xml": 읽어들일 XML 설정 파일의 위치
		context = new GenericXmlApplicationContext("classpath:hello-bean.xml");
	}
	
	// Constructor Injection을 테스트하는 메서드
	@Test
	void helloBeanCons() {
		Hello hello = context.getBean("helloC", Hello.class);
		assertEquals("Hello 생성자", hello.sayHello());
		hello.print();
	}
	
	// 이 메서드가 단위 테스트용 코드임을 JUnit에게 알려주는 어노테이션
	// Setter Injection을 테스트하는 메서드
	@Test @Disabled
	void helloBeanSetter() {
		Hello helloById = (Hello)context.getBean("hello");
		Hello helloByType = context.getBean("hello", Hello.class);
		
		// Hello SpringBean이 Singleton 객체인지를 검증하기
		System.out.println(helloById == helloByType);
		
		assertSame(helloById, helloByType);
		
		assertEquals("Hello 스프링", helloByType.sayHello());
		
		helloByType.print();
		
		Printer strPrinter = context.getBean("strPrinter", Printer.class);
		assertEquals("Hello 스프링", strPrinter.toString());
	}

}
