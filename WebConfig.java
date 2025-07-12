package com.jpaproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer  {

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        
		registry.addInterceptor(new LoginInterceptor())     
    	
		// 로그인 처리 안되면 무조건 감지해야 할 파일들
    	.addPathPatterns("/Inotice/**")
        .addPathPatterns("/Gnotice/**")
            
        // 세션감지 제외 파일들
        .excludePathPatterns("/login/**", "/css/**", "/js/**");
    }
}
