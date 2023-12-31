package ru.hogwarts.school.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;

import java.util.stream.Stream;

@RestController
@RequestMapping("/info")
public class InfoController {

    private final Logger logger = (Logger) LoggerFactory.getLogger(InfoController.class);
    @Value("${server.port}")
    private String port;

    @GetMapping("/getPort")
    public String getPort() {
        return port;
    }

    @GetMapping("/sum")
    public void getSum() {
        long start = System.currentTimeMillis();
        int sum = Stream.iterate(1, a -> a +1)
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b );
        long finish = System.currentTimeMillis();

        logger.info("Result: {}, execution time: {}", sum, finish - start);

    }

    @GetMapping("/sum-parallel")
    public void getSumParallel() {
        long start = System.currentTimeMillis();
        int sum = Stream.iterate(1, a -> a +1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b );
        long finish = System.currentTimeMillis();

        logger.info("Parallel result: {}, execution time: {}", sum, finish - start);

    }

}
