package com.idev.boot.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Board {
	private int idx;
	private String writer;
	private String password;
	private String subject;
	private String content;
	private int readCount;
	private int commentCount;
	private String ip;
	private LocalDateTime wdate;
	
}

//@Data : getter,setter,toString
//Model객체는 반드시 getter/setter 가 있어야합니다.(주의)
