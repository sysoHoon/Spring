package day1.annot;

import org.springframework.stereotype.Component;

@Component
public class BuyService {	// bean id는 buyService
	
	private BuyDao dao;		//의존관계의 클래스
	
	// 어노테이션 @Compoment 를 사용할때
	// 커스텀 생성자가 있다면 의존관계가 자동(타입으로 매칭)으로 주입된다.
	// 커스텀 생성자가 있기 때문에 @Autowired 생략 가능
	public BuyService(BuyDao dao) {
		System.out.println("BuyService 생성자 ************");
		this.dao=dao;
	}
	
	public void setDao(BuyDao dao) {
		this.dao = dao;
	}
	
	//일반 메소드
	public void buy() {
		System.out.println("service buy()");
		dao.buy();
	}
	

}
