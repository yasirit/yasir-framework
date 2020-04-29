package net.cn.yasir.framework.restful.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

/**
 * FastJson格式化输出
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/4/28
 */
@Configuration
public class FastJsonConfig  {

    @Bean
    public HttpMessageConverters httpMessageConverters() {
        return new HttpMessageConverters(formatHttpMessageConverter());
    }

    public static FastJsonHttpMessageConverter formatHttpMessageConverter () {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //创建配置类
        com.alibaba.fastjson.support.config.FastJsonConfig fastJsonConfig = new com.alibaba.fastjson.support.config.FastJsonConfig();
        //修改配置返回内容的过滤
        fastJsonConfig.setSerializerFeatures(
                //消除对同一对象循环引用
                SerializerFeature.DisableCircularReferenceDetect,
                //结果格式化
                SerializerFeature.PrettyFormat,
                //字符类型字段为null,输出为"",而非null
                SerializerFeature.WriteNullStringAsEmpty,
                //数值字段如果为null,输出为0,而非null
                SerializerFeature.WriteNullNumberAsZero,
                //Boolean字段如果为null,输出为false,而非null
                SerializerFeature.WriteNullBooleanAsFalse,
                //全局修改日期格式
                SerializerFeature.WriteDateUseDateFormat,
                //按字段名称排序后输出
                SerializerFeature.SortField,
                //Map中key为null不显示
                SerializerFeature.WriteMapNullValue,
                //List字段如果为null,输出为[],而非null
                SerializerFeature.WriteNullListAsEmpty
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        return fastConverter;
    }
}
