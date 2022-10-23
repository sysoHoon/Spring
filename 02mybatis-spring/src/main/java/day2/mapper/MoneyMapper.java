package day2.mapper;

import java.util.List; 

import org.apache.ibatis.annotations.Select;

import day2.vo.Money;
import day2.vo.Sale;

public interface MoneyMapper {
	List<Money> getAll();
	@Select("select * from money_tbl_02 where salenol=#{salenol}")
	Money getOne(int salenol);
	
	@Select("  	select met.custno, custname,\n"
			+ "		   decode(met.grade, 'A', 'VIP', 'B', '일반', 'C', '직원') as agrade,\n"
			+ "		   asum\n"
			+ "   from member_tbl_02 met join\n"
			+ "		   (select custno, sum(price) asum from money_tbl_02 mot \n"
			+ "		   group by custno\n"
			+ "		   order by asum desc) sale\n"
			+ "   on met.custno = sale.custno ORDER BY asum DESC")
	List<Sale> getSales();
}
