package net.cn.yasir.framework.restful.common;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @desc: 控制器 - 扩展
 * @author: yasir
 * @date: 2020/3/7 11:49
 */
public abstract class BaseResp implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseResp.class);

    protected  <T> WebJsonObjectResp<T> success() {
        return new WebJsonObjectResp(ExceptionCode.SUCCESS);
    }

    protected <T> WebJsonObjectResp<T> success(T data) {
        return new WebJsonObjectResp(ExceptionCode.SUCCESS, data);
    }

    protected <T> WebJsonObjectResp<T> success(String msg, T data) {
        return new WebJsonObjectResp(ExceptionCode.SUCCESS.getCode(), msg, data);
    }

    protected <T> WebJsonObjectResp<T> fail(int code, String msg) {
        return new WebJsonObjectResp(code, msg);
    }

    protected <T> WebJsonObjectResp<T> fail(ReturnCode returnCode) {
        return new WebJsonObjectResp(returnCode.getCode(), returnCode.getMsg());
    }

    protected <T> T getMockData(String filePath, Class<T> clazz) throws YaSirException {
        return JSON.parseObject(this.getFileContent(filePath), clazz);
    }

    protected <T> List<T> getMockListData(String filePath, Class<T> clazz) throws YaSirException {
        return JSON.parseArray(this.getFileContent(filePath), clazz);
    }

    private String getFileContent(String filePath) throws YaSirException {
        try {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("classpath:mock-data/" + filePath);
            if (resources.length <= 0) {
                return null;
            } else {
                Resource[] var8 = resources;
                int var9 = resources.length;
                for(int var10 = 0; var10 < var9; ++var10) {
                    Resource r = var8[var10];
                    String fileName = r.getFilename();
                    InputStream is = r.getInputStream();
                    List<Byte> byteList = new ArrayList();
                    byte[] readByte = new byte[is.available()];
                    byte[] fileBytes;
                    int j;
                    while(is.read(readByte) > 0) {
                        fileBytes = readByte;
                        j = readByte.length;

                        for(int var14 = 0; var14 < j; ++var14) {
                            byte b = fileBytes[var14];
                            byteList.add(b);
                        }
                    }
                    if (!CollectionUtils.isEmpty(byteList)) {
                        fileBytes = new byte[byteList.size()];

                        for(j = 0; j < byteList.size(); ++j) {
                            fileBytes[j] = (Byte)byteList.get(j);
                        }

                        String jsonStr = (new String(fileBytes)).trim();
                        LOGGER.debug("Mock data: {}, {}", fileName, jsonStr);
                        return jsonStr;
                    }
                    LOGGER.warn("文件的内容为空：" + fileName);
                }
                return null;
            }
        } catch (Exception var16) {
            LOGGER.error(var16.getMessage(), var16);
            throw new YaSirException(ExceptionCode.SYSTEM_ERROR);
        }
    }
}
