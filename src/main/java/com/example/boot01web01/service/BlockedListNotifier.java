package com.example.boot01web01.service;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class BlockedListNotifier {
    @EventListener
    @Async
    public void process(BlockedListEvent event) {
        // notify appropriate parties via notificationAddress...
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(event);
    }
}