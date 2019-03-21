package com.xin.thread;

import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Three
 * @since 18/6/20上午11:47
 */
@SpringBootApplication

public class SpringBootThreadPool {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(SpringBootThreadPool.class, args);
        Task               task    = context.getBean(Task.class);
        task.task1();
        task.task2();
        task.task3();

    }
}

@Configuration
@EnableAsync
class TaskPoolConfig {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(10);//核心线程数10：线程池创建时候初始化的线程数
        executor.setMaxPoolSize(20);//最大线程数20：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
        executor.setQueueCapacity(200);//缓冲队列200：用来缓冲执行任务的队列
        executor.setKeepAliveSeconds(60);//允许线程的空闲时间60秒：当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
        executor.setThreadNamePrefix("taskExecutor-");//线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());//线程池对拒绝任务的处理策略：这里采用了CallerRunsPolicy策略，当线程池没有处理能力的时候，该策略会直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务
        return executor;
    }
}

@Component
@Log4j
class Task {
    @Async("taskExecutor")
    void task1() throws Exception {
        log.info("任务一开始");
        Thread.sleep(RandomUtils.nextInt(10000, 15000));
        log.info("任务一结束");
    }

    @Async("taskExecutor")
    void task2() throws Exception {
        log.info("任务二开始");
        Thread.sleep(RandomUtils.nextInt(5000, 10000));
        log.info("任务二结束");
    }

    @Async("taskExecutor")
    void task3() throws Exception {
        log.info("任务三开始");
        Thread.sleep(RandomUtils.nextInt(3000, 5000));
        log.info("任务三结束");
    }
}