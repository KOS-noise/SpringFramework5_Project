package mysrping.di.xml.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.Resource;

import myspring.di.xml.Hello;
import myspring.di.xml.Printer;
import myspring.di.xml.config.HelloConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = HelloConfig.class)
public class HelloConfigTest {
	@Autowired
	Hello hello;
	
	@Resource(name="stringPrinter")
	Printer printer;
	
	@Test
	void hello() {
		
		assertEquals("Hello 자바컨피그", hello.sayHello());
		hello.print();
		assertEquals("Hello 자바컨피그", printer.toString());
		
	}
}