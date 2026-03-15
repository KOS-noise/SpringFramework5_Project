package myspring.di.xml; // 이 클래스가 속한 폴더(패키지) 위치입니다.

import java.util.List; // 여러 개의 데이터를 담는 List 기능을 사용하기 위해 가져옵니다.

public class Hello {
	// [멤버 변수] : 클래스 내부에서 사용할 데이터들입니다.
	String name;          // 인사할 대상의 이름
	Printer printer;      // 콘솔이나 파일 등에 출력 기능을 담당할 객체 (인터페이스 타입일 확률이 높음)
	List<String> names;   // 여러 명에게 인사할 경우를 대비한 이름 리스트

	// [기본 생성자] : 스프링 컨테이너가 객체를 처음 생성할 때 호출됩니다.
	public Hello() {
		// 현재 클래스의 이름을 출력해서 객체가 만들어졌음을 확인합니다.
		System.out.println(this.getClass().getName() + "기본생성자 호출됨!");
	}

	// [오버로딩 생성자] : 생성자를 통해 이름과 Printer 객체를 한 번에 주입받을 때 사용합니다. (생성자 주입)
	public Hello(String name, Printer printer) {
		System.out.println(this.getClass().getName() + "오버로딩 생성자 호출됨!");
		this.name = name;
		this.printer = printer;
	}

	// names 리스트를 가져오는 메서드 (Getter)
	public List<String> getNames() {
		return this.names;
	}

	// names 리스트를 외부에서 넣어주는 메서드 (Setter 주입용)
	public void setNames(List<String> list) {
		this.names = list;
	}

	// 이름을 설정하는 메서드 (Setter 주입)
	// XML 설정에서 <property name="name" value="Sodam" /> 식으로 작성하면 호출됩니다.
	public void setName(String name) {
		System.out.println("setName() 메서드 호출됨 : " + name);
		this.name = name;
	}

	// 사용할 Printer 객체를 설정하는 메서드 (Setter 주입)
	// 외부에서 만들어진 Printer 객체를 이 클래스 안으로 전달받습니다.
	public void setPrinter(Printer printer) {
		System.out.println("setPrinter() 메서드 호출됨 : " + printer.getClass().getName());
		this.printer = printer;
	}

	// 인사말 문자열을 생성하는 비즈니스 로직
	public String sayHello() {
		return "Hello " + name;
	}

	// 주입받은 printer 객체의 print 기능을 사용해 인사말을 출력합니다.
	public void print() {
		this.printer.print(sayHello());
	}
}