package com.example.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3); // Đặt kích thước cốt lõi là 3
        executor.setMaxPoolSize(3); // Đặt kích thước tối đa là 3
        executor.setQueueCapacity(100); // Đặt dung lượng hàng đợi là 100
        executor.setThreadNamePrefix("MyExecutor-"); // Đặt tiền tố tên luồng
        executor.initialize(); // Khởi tạo executor
        return executor;
    }
}
