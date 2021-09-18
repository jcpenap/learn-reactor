package com.learngreactorspring.fluxandmonoplayground;


import reactor.core.publisher.Flux;

public class FluxAndMonoTest {
    @org.junit.Test
    public void fluxtest(){
        Flux<String> stringFlux = Flux.just("Spring", "Spring boot", "Reactive Spring")
                /*.concatWith(Flux.error(new RuntimeException("Exception Occurred")))*/
                .concatWith(Flux.just("After Error"))
                .log();
        stringFlux.subscribe(System.out::println,
                (e) -> System.err.println("Exception is " + e),
                () -> System.out.println("Completed"));
    }
}
