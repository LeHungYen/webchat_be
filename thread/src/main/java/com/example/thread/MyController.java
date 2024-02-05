package com.example.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.CompletableFuture;

@RestController
public class MyController {

    @Autowired
    private MyService myService;

    @GetMapping("/doAsyncTasks")
    public CompletableFuture<String> doAsyncTasks() {
        CompletableFuture<String> result1 = myService.asyncMethod1();
        CompletableFuture<String> result2 = myService.asyncMethod2();
        CompletableFuture<String> result3 = myService.asyncMethod3();

        // Kết hợp kết quả từ các CompletableFuture
        CompletableFuture.allOf(result1, result2, result3).join();

        // Kết hợp kết quả và trả về
        return CompletableFuture.completedFuture(
                result1.join() + "\n" + result2.join() + "\n" + result3.join()
        );
    }
}
