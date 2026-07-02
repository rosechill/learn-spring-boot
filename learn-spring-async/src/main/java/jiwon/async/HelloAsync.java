package jiwon.async;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Component
@Slf4j
public class HelloAsync {

    @Async("singleTaskExecutor")
    @SneakyThrows
    public Future<String> hello(final String name) throws InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        future.complete("Hello " + name + " from Thread " + Thread.currentThread().getName());
        return future;
    }

    @Async
    @SneakyThrows
    public void hello() {
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        log.info("Hello after 2 second {}", Thread.currentThread().getName());
    }

}
