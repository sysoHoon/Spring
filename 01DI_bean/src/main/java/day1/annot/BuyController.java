package day1.annot;

import org.springframework.stereotype.Component;

@Component		// bean id는 buyController
public class BuyController {
	
	private BuyService service;		
	
	// 어노테이션 @Compoment 를 사용할때
	// 커스텀 생성자가 있다면 의존관계가 자동(타입으로 매칭)으로 주입된다.
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
