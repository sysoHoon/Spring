package day2.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class Member {
	
	private int custNo;
	private String custName;
	private String phone;
	private String address;
	private Date joinDate;
	private String grade;
	private String city;
	
	
}
