package com.hy.webTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// @EnableJpaAuditing // jpa adjusting 어노테이션 활성화
@SpringBootApplication // 스프링 부트, Spring Bean 읽기와 생성 자동으로 설정 (위치부터 설정까지 읽어가기때문에 항상 프로젝트 최상단에 생성)
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args); // 내장 WAS 실행 -> 항상 같은 환경에서 스프링 부트 배포를 가능하게 함
    }
}