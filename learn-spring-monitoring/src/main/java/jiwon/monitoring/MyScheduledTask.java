package jiwon.monitoring;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class MyScheduledTask {

    @Autowired
    private MeterRegistry meterRegistry;

    @Scheduled(fixedRate = 10L, timeUnit = TimeUnit.SECONDS)
    public void hello() {
        meterRegistry.counter("my.scheduled.task").increment();
        log.info("Hello World");
    }

}
