package com.idev.boot.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comments {
	private Integer idx;
	private int mref;
	private String writer;
	private String password;
	private String content;
	private LocalDateTime wdate;
	private String ip;
}
