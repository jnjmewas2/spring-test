package com.example.search.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor


@Configuration
@EnableAsync
class AsyncConfig {
    @Bean(name = arrayOf("asyncExecutor"))
    fun threadPoolTaskExecutor(): Executor? {
        val taskExecutor = ThreadPoolTaskExecutor()
        taskExecutor.corePoolSize = 3
        taskExecutor.maxPoolSize = 30
        taskExecutor.setQueueCapacity(100)
        taskExecutor.setThreadNamePrefix("AsynchThread-")
        taskExecutor.initialize()
        return taskExecutor
    }
}