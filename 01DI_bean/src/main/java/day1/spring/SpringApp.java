package day1.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import day1.review.BuyController;
import day1.review.ProductDao;
import day1.review.ProductService;

public class SpringApp {

	public static void main(String[] args) {
		//빈설정 파일을 읽어와서 빈(객체)를 생성합니다.spring-context가 빈을 관리합니다.
		ClassPathXmlApplicationContext context =
		new ClassPathXmlApplicationContext
		("classpath:META-INF/spring/applicationContext.xml");		
		
		//위에서 만들어진 bean(객체) 중에서 id가 controller 인 bean을 가져와 변수에 참조합니다.
		//getBean메소드 리턴타입이 Object : 참조타입에 맞게 캐스팅이 필요합니다.
		BuyController controller =
			(BuyController)context.getBean("controller");
		
		controller.buy();
		System.out.println("---------------------");
		
//		ProductDao pdao =(ProductDao) context.getBean("productDao2");
//		pdao.produce();
		
		
		ProductService pservice = (ProductService) context.getBean("productService");
		pservice.produce();

		context.close();
	}

}
/*
BuyDao 생성자***********
BuyService 생성자 ************
BuyController 생성자 *************
controller buy() ~~~~ 
service buy()
dao buy()~~~~~~
마이바티스를 이용하여 db에 연결합니다.
 * 
 */ 
