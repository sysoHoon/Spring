package day1.review;

public class ProductDao {
	
	private int cnt;
	private String pname;
	
	public ProductDao() {
		System.out.println("ProductDao 기본 생성자******");
	}
	// 커스텀 생성자가 없습니다.
	public void setCnt(int cnt) {	//기본데이터타입
		this.cnt = cnt;
	}
	
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	public void produce() {
		System.out.println("dao produce() ~~~~~~");
		System.out.println("상품["+pname +"]을 " + cnt + " 수량으로 생산합니다.");
	}
}
