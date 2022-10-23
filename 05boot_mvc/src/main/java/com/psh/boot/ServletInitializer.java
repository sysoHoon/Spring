package com.psh.boot;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// 스프링 부트의 기본 view 템플릿엔진(.html), jsp 사용하기 위해 필요한 설정 - 서블릿 초기화
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
// 서블릿 : 웹 애플리케이션의 html 문서를 java로 작성 -> .class 컴파일한 결과로 html이 만들어진다.(jsp)
// jsp -> .java -> .class -> html 문서 생성
}
