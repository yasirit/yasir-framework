package net.cn.yasir.framework.restful.handler;

import jodd.io.StreamUtil;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Http请求参数转换器
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/1/16
 */
public class HttpServletRequestWrapperHandler extends HttpServletRequestWrapper {

    private final byte[] body;

    public HttpServletRequestWrapperHandler(HttpServletRequest request)
            throws IOException {
        super(request);
        body = StreamUtil.readBytes(request.getReader(), "utf-8");
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);
        return new ServletInputStream() {

            @Override
            public int read() {
                return byteArrayInputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener arg0) {

            }
        };
    }

}
