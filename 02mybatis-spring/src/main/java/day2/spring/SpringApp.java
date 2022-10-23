 package day2.spring;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import day2.mapper.MemberMapper;
import day2.mapper.MoneyMapper;
import day2.vo.Member;
import day2.vo.Money;

public class SpringApp {

	public static void main(String[] args) {

		AbstractApplicationContext context =
				new ClassPathXmlApplicationContext
				("classpath:META-INF/spring/applicationContext.xml");
		
		//데이터베이스 연결이 되는지 확인
		System.out.println("1.-------mybatis-spring 동작확인 ---------");
		SqlSessionTemplate sqlSessionTempl = 
				(SqlSessionTemplate)context.getBean("sqlSessionTemplate");
		System.out.println(sqlSessionTempl);    //null 아닌값으로 출력이 있으면 연결 성공
		
		// MemberMapper 은 인터페이스이지만 mybatis-spring은 인터페이스를 이용하여 인터페이스 구현체(객체)를 생성,
		// 자동으로 데이터베이스의 테이블 컬럼과 Member 클래스를 연결하는 mapper역할 bean 을 생성하고 
		// 이것이 sql을 실행합니다.
		// bean 이름은 인터페이스 이름과 동일하고 첫글자만 소문자
		System.out.println("2.------ Mapper 인터페이스 구현체 테스트 ---------");
		MemberMapper mapper 
		= (MemberMapper)context.getBean("memberMapper");
		List<Member> list = mapper.selectAll();
		System.out.println(list);
		
		MoneyMapper money_mapper 
		= (MoneyMapper)context.getBean("moneyMapper");
		List<Money> money_list = money_mapper.getAll();
		System.out.println(money_list);
		
		System.out.println("3.------ @Select  테스트 ---------");
		System.out.println(money_mapper.getOne(20160005));
		System.out.println(money_mapper.getSales());
		
		context.close();
		
		
		
	}

}
