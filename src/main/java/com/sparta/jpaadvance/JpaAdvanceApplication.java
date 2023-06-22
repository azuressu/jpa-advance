package com.sparta.jpaadvance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // 시간 사용을 위한 애너테이션
@SpringBootApplication
public class JpaAdvanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaAdvanceApplication.class, args);
    }

}
