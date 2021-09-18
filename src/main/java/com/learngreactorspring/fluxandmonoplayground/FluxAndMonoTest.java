package com.learngreactorspring.fluxandmonoplayground;


import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoTest {
    @Test
    public void fluxtest(){
        Flux<String> stringFlux = Flux.just("Spring", "Spring boot", "Reactive Spring")
                /*.concatWith(Flux.error(new RuntimeException("Exception Occurred")))*/
                .concatWith(Flux.just("After Error"))
                .log();
        stringFlux.subscribe(System.out::println,
                (e) -> System.err.println("Exception is " + e),
                () -> System.out.println("Completed"));
    }

    @Test
    public void fluxTestElements_WithoutErrors() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring boot", "Reactive Spring")
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring boot")
                .expectNext("Reactive Spring")
                .verifyComplete();
    }

    @Test
    public void fluxTestElements_WithErrors() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring boot")
                .expectNext("Reactive Spring")
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    public void fluxTestElements_WithErrorMessage() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring boot")
                .expectNext("Reactive Spring")
                .expectErrorMessage("Exception Occurred")
                .verify();
    }

    @Test
    public void fluxTestElementsCountsExpected() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring boot", "Reactive Spring")
                .log();

        StepVerifier.create(stringFlux)
                .expectNextCount(3)
                .verifyComplete();
    }
}
