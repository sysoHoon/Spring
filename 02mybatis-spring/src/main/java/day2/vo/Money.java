package day2.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Money {
	private int custno;
	private int salenol;
	private int pcost;
	private int amount;
	private String pcode;
	
}
