package reactor.learning;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.stream.Stream;

public class HelloWorld {

    public static void main(String[] args) {
        //实例化
        Flux.just(1, 2, 3, 4, 5, 6);
        Mono.just(1);
        Stream<Integer> stream = new ArrayList<Integer>().stream();
        Flux.fromStream(stream);

        //简单累加，步骤化
        Integer params = 1;
        Mono<Integer> mono = Mono.just(params)
                .flatMap(v -> {
                    return Mono.just(v + 1);
                })
                .flatMap(v -> {
                    return Mono.just(v + 1);
                })
                .flatMap(v -> {
                    return Mono.just(v + 1);
                }).map((v) -> {
                    return v + 4;
                });
        mono.subscribe((v) -> {
            System.out.println(v);
        });

        //异步并行
        Mono<String> hello1 = Mono.just("hello1");
        Mono<String> hello2 = Mono.just("hello2");
        Mono<String> combare = Mono.zip(hello1, hello2, (p1, p2) -> {
            return p1 + p2;
        });
        //等待执行
        combare.subscribe(s -> {
            System.out.println(s);
        });
    }
}
