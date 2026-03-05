package myspring.di.annot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.Resource;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:hello-annot.xml")
public class HelloBeanSpringTest {
	
	@Autowired
	HelloBean hello;
	
	@Resource(name = "stringPrinter")
	IPrinter printer;
	
	@Autowired
	HelloBeanCons helloCons;
	
	
	@Test
	void helloBeanSetter() {
		assertEquals("Hello 어노테이션", hello.sayHello());
		hello.print();
		assertEquals("Hello 어노테이션", printer.toString());
		
	}
	
	
	@Test
	void helloBeanCons() {
		assertEquals("Hello 생성자어노테이션", helloCons.sayHello());
		helloCons.print();
	}

}
