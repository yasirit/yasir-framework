package net.cn.yasir.framework.restful.filter;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * 设置context
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/5/11
 */
public class HttpSetContextFilter implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        //todo
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
