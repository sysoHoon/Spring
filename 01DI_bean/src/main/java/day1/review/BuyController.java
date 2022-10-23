package day1.review;


public class BuyController {
	
	
	private BuyService service;		
				//프로퍼티 변수가 클래스 타입 일때 의존관계가 생깁니다.
				//BuyService 클래스를 사용하는 것을 "의존관계가 있다" 고 표현 합니다.
	
	//프로퍼티에 객체를 참조시키는 방법(객체를 언제 참조 시킬것이냐?)  1) 생성자  2) setter
	//-> 클래스 타입일때는 이것을 "의존관계 주입"(Dependency Injection, DI) 이라고 합니다.
	public BuyController(BuyService service) {
		System.out.println("BuyController 생성자 *************");
		this.service=service;	// 객체 참조
	}
	
	public void setService(BuyService service) {
		this.service = service;		// 객체 참조
	}
	
	public void buy() {
		System.out.println("controller buy() ~~~~ ");
		service.buy();		// 참조되는 객체가 없으면? service 변수는 null -> NullPointerException
	}
	
}
