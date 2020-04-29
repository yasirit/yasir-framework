package net.cn.yasir.framework.job.auto;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import net.cn.yasir.framework.job.properities.XxlJobProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * XXL-JOB自动配置
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/4/29
 */
@Configuration
@EnableConfigurationProperties(XxlJobProperties.class)
public class XxlJobAutoConfiguration {

    private Logger logger = LoggerFactory.getLogger(XxlJobAutoConfiguration.class);

    @Bean
    @ConditionalOnProperty(prefix = "yasir.job", name = {"enable"}, havingValue = "true")
    public XxlJobSpringExecutor xxlJobExecutor(XxlJobProperties xxlJobProperties) {
        logger.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(xxlJobProperties.getAdminAddresses());
        xxlJobSpringExecutor.setAppName(xxlJobProperties.getAppName());
        xxlJobSpringExecutor.setIp(xxlJobProperties.getIp());
        xxlJobSpringExecutor.setPort(xxlJobProperties.getPort());
        xxlJobSpringExecutor.setAccessToken(xxlJobProperties.getAccessToken());
        xxlJobSpringExecutor.setLogPath(xxlJobProperties.getLogPath());
        xxlJobSpringExecutor.setLogRetentionDays(xxlJobProperties.getLogRetentionDays());
        return xxlJobSpringExecutor;
    }

}
