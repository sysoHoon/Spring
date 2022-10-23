package day1.annot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProductDao {	// bean id는 productDao
	@Value(value="11")
	private int cnt;
	@Value(value="iphoneX")	// 아래 변수에 값을 직접 대입하는 어노테이션.(setter와 무관)
	private String pname;
	
	public ProductDao() {
		System.out.println("ProductDao 기본 생성자******");
	}
	// 커스텀 생성자가 없습니다.
	public void setCnt(int cnt) {	//기본 데이터타입
		this.cnt = cnt;
	}
/*	
	public void setPname(String pname) {
		this.pname = pname;
	}
*/
	
	public void produce() {
		System.out.println("dao produce() ~~~~~~");
		System.out.println("상품["+pname +"]을 " + cnt + " 수량으로 생산합니다.");
	}
}
