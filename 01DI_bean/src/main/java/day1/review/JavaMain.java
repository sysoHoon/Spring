package day1.review;


public class JavaMain {
	public static void main(String[] args) {
		
		//controller 클래스 테스트
		BuyDao dao = new BuyDao("Mybatis");
		BuyService service = new BuyService(dao);	//의존관계 주입
		BuyController shop = new BuyController(service);  //의존관계 주입
		
		//위와 같이 객체를 생성하고 의존관계를 설정하는 것을 객체들의 강한 결합.
		// -> main 소스에서 설정.
		shop.buy();
		
		
		//애플리케이션 실행은 여러환경에서 실행하게 됩니다. 그리고 데이베이스 연결도 다른서버 다른계정들을
		//사용하면서 실행이 됩니다.
		//controller에서 사용하는 service 클래스를 변경해야한다거나
		//service에서 사용하는 dao클래스를 변경해야하는 상황들이 많이 생기는데
		//이럴때 어떻게 변경? 기본방법은 코드를 수정-> 비효율적
		
		//spring 프레임웍은 의존관계 주입을 spring에서 관리는 하는 방법으로 합니다.
	}
// Controller는 사용자 요청을 받고 요청에 따른 비지니스 로직은 Service에서 실행
// 비지니스 로직을 처리하는 Service에서 데이터베이스 등 물리적 저장소의 데이터를 처리해야한다면 그것은 Dao에서 한다.
// (Service는 필요에 따라서 사용. 없을 수도 있음.)
}
