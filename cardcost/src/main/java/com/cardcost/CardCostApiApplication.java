package com.cardcost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CardCostApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardCostApiApplication.class, args);
    }

//    @Bean
//    public Customizer<Resilience4JCircuitBreakerFactory> globalCustomConfiguration() {
//        TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig
//                .custom()
//                .timeoutDuration(Duration.ofSeconds(4))
//                .build();
//        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig
//                .custom()
//                .failureRateThreshold(50)
//                .waitDurationInOpenState(Duration.ofMillis(1000))
//                .slidingWindowSize(2)
//                .build();
//
//        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
//                .timeLimiterConfig(timeLimiterConfig)
//                .circuitBreakerConfig(circuitBreakerConfig)
//                .build());
//    }
//
//    @Bean
//    public Customizer<Resilience4JCircuitBreakerFactory> specificCustomConfiguration1() {
//
//        TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig
//                .custom()
//                .timeoutDuration(Duration.ofSeconds(4))
//                .build();
//        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig
//                .custom()
//                .failureRateThreshold(50)
//                .waitDurationInOpenState(Duration.ofMillis(1000))
//                .slidingWindowSize(2)
//                .build();
//
//        return factory -> factory.configure(builder -> builder
//                .circuitBreakerConfig(circuitBreakerConfig)
//                .timeLimiterConfig(timeLimiterConfig).build(), "circuitBreaker");
//    }
//
//    @Bean
//    public Customizer<Resilience4JCircuitBreakerFactory> specificCustomConfiguration2() {
//
//        TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig
//                .custom()
//                .timeoutDuration(Duration.ofSeconds(4))
//                .build();
//        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig
//                .custom()
//                .failureRateThreshold(50)
//                .waitDurationInOpenState(Duration.ofMillis(1000))
//                .slidingWindowSize(2)
//                .build();
//
//        return factory -> factory.configure(builder -> builder
//                .circuitBreakerConfig(circuitBreakerConfig)
//                .timeLimiterConfig(timeLimiterConfig)
//                .build(), "circuitBreaker1", "circuitBreaker2", "circuitBreaker3");
//    }
}
