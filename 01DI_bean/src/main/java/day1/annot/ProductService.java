package day1.annot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductService {
	
//	@Autowired
	private ProductDao dao;	// 커스텀 생성자가 없을때 dao에 의존관계에 자동주입?
							// 자동 주입 @Autowired 어노테이션 필요함. - 변수 선언 또는 setter 택 1
	public ProductService() {
		System.out.println("ProduceService 기본 생성자*************");
	}
	
	@Autowired			// 권고는 이 위치에 @Autowired
	public void setDao(ProductDao dao) {	//의존관계 주입
		this.dao = dao;
	}
	
	public void produce() {
		System.out.println("service produce() ~~~~~~");
		dao.produce();
	}
	
}
