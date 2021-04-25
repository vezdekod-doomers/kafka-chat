package com.alesharik.hack1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Deque;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;

@Component
public class Runner {
    @Autowired
    private KafkaTemplate<String, String> template;
    private final Deque<String> dedup = new LinkedBlockingDeque<>();

    public void run() {
        System.out.println("Started. You can chat not!");
        var scanner = new Scanner(System.in);
        while (true) {
            var line = scanner.nextLine();
            dedup.add(line);
            template.send("msg", line);
        }
    }

    @KafkaListener(topics = "msg")
    public void listenGroupFoo(String message) {
        if (Objects.equals(dedup.peek(), message)) {
            dedup.poll();
        } else {
            System.out.println("MSG " + message);
        }
    }
}
