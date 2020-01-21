package net.cn.yasir.framework.restful.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisConfig
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/1/21
 */
@Configuration
public class MybatisConfig {
    /**
     * 分页
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }

//    /**
//     * Sequence主键
//     * @return
//     */
//    @Bean
//    public IKeyGenerator keyGenerator() {
//        return new H2KeyGenerator();
//    }

}
