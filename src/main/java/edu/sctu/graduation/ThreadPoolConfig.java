package edu.sctu.graduation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 线程池配置 主要作用在于Spring启动时自动加载一个ExecutorService对象. 这样线程池由spring统一管理，方便业务程序调用
 * <p>在@service 中注入 ExecutorService 对象就可以使用</p>
 * @author zsw
 *
 */
@Configuration
public class ThreadPoolConfig {
	private static final Logger logger=Logger.getLogger(ThreadPoolConfig.class);
	@Bean
    public ExecutorService getThreadPool(){
		logger.info("################系统启动设置20个线程在线程池中#######################");
        return Executors.newFixedThreadPool(20);
    }
}
