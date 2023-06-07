package hello.example.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// 자동으로 Bean 등록해주는 ComponentScan
@Configuration
// @Component 가 붙은 모든 클래스를 스프링 빈으로 등록한다.
@ComponentScan(
        // 특정 경로에 있는 Component 만 실행, default = hello.example.core
//        basePackages = "hello.example.core.member",

        // AppConfig 를 제외하기 위한 설정
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
