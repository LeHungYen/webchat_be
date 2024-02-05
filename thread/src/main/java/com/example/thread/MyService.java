package com.example.thread;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

@Service
public class MyService {

    @Async("taskExecutor")
    public CompletableFuture<String> asyncMethod1() {
        // Giả lập công việc mất thời gian
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture("Kết quả của tác vụ 1");
    }

    @Async("taskExecutor")
    public CompletableFuture<String> asyncMethod2() {
        // Giả lập công việc mất thời gian
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture("Kết quả của tác vụ 2");
    }

    @Async("taskExecutor")
    public CompletableFuture<String> asyncMethod3() {
        // Giả lập công việc mất thời gian
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture("Kết quả của tác vụ 3");
    }
}
