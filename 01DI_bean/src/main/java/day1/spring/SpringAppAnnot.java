package day1.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import day1.annot.BuyController;
import day1.annot.BuyDao;
import day1.annot.ProductDao;
import day1.annot.ProductService;


public class SpringAppAnnot {

	public static void main(String[] args) {
		// 스프링 컨테이너가 bean 설정 파일을 읽어와서 bean(객체)를 생성한다. spring-context가 bean을 관리한다.
		ClassPathXmlApplicationContext context =
		new ClassPathXmlApplicationContext
		("classpath:META-INF/spring/applicationContextAnnot.xml");		
		
		System.out.println("1.---------------------");
		BuyController controller =
			(BuyController)context.getBean("buyController");
		// @Component의 bean id는 클래스 이름에서 첫글자만 소문자로 설정되는 것이 기본
		controller.buy();
		System.out.println("2.---------------------");
//		BuyDao dao = (BuyDao) context.getBean("buyDao");	// bean id로 가져오기
		BuyDao dao = context.getBean(BuyDao.class);		// bean 타입으로 가져오기(해당 타입이 1개일때)
		dao.buy();
		
		System.out.println("3.---------------------");
		ProductDao pdao = context.getBean(ProductDao.class);
		pdao.produce();
		
		System.out.println("4.---------------------");
		ProductService pservice = (ProductService) context.getBean(ProductService.class);
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
