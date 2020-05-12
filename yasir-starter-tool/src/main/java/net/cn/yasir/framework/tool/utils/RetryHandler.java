package net.cn.yasir.framework.tool.utils;

import net.cn.yasir.framework.tool.component.WebJsonObjectResp;
import net.cn.yasir.framework.tool.component.YaSirException;
import net.cn.yasir.framework.tool.enums.EnableEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 重试处理器
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/3/23
 */
public abstract class RetryHandler {

    private static final Logger log = LoggerFactory.getLogger(RetryHandler.class);

    /**
     * 重试次数key
     */
    public static final String RETRY_COUNT_KEY = "retryCount";

    /**
     * 业务处理状态key
     */
    public static final String HANDLER_STATUS_KEY = "handlerStatus";

    /**
     * 异常日志key
     */
    public static final String ERROR_LOG_KEY = "errorLog";

    /**
     * 业务响应对象key
     */
    public static final String RTN_OBJ_KEY = "rtnObj";

    /**
     * 默认重试次数
     */
    private static final int DEFAULT_RETRY_TIME = 1;

    /**
     * 重试次数
     */
    private int retryTime = DEFAULT_RETRY_TIME;

    /**
     * 重试的睡眠时间
     */
    private int sleepTime = 0;

    public int getSleepTime() {
        return sleepTime;
    }

    public RetryHandler setSleepTime(int sleepTime) {
        if(sleepTime < 0) {
            throw new IllegalArgumentException("sleepTime should equal or bigger than 0");
        }
        this.sleepTime = sleepTime;
        return this;
    }

    public int getRetryTime() {
        return retryTime;
    }

    public RetryHandler setRetryTime(int retryTime) {
        if (retryTime <= 0) {
            throw new IllegalArgumentException("retryTime should bigger than 0");
        }
        this.retryTime = retryTime;
        return this;
    }

    /**
     * 重试的业务执行代码
     * @return
     * @throws Exception
     */
    protected abstract WebJsonObjectResp doRetry() throws Exception;

    /**
     * 执行业务代码
     *
     * @return
     * @throws InterruptedException
     */
    public Map<String, Object> execute() {
        /**
         * 返回结果集
         * key：retryCount 实际重试次数
         * key：handlerStatus 业务处理状态 1成功0失败
         * key：rtnObj 返回响应对象
         * key：errorLog 异常日志key
         */
        Map<String, Object> map = new HashMap<>(16);
        Integer handlerStatus = EnableEnum.DISABLE.getValue();
        StringBuffer errorLog = new StringBuffer();
        WebJsonObjectResp rtnObj = null;
        Integer retryCount = 0;
        for (int i = 0; i < retryTime && EnableEnum.DISABLE.getValue() == handlerStatus; i++) {
            try {
                retryCount = i;
                rtnObj = doRetry();
                if(rtnObj.getCode() == 200L) {
                    handlerStatus = EnableEnum.ENABLE.getValue();
                } else {
                    throw new YaSirException(rtnObj.getCode(), rtnObj.getMsg());
                }
            } catch (Exception e) {
                errorLog.append(e.getMessage()).append("|");
                if(sleepTime > 0) {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e1) {
                        log.error("重试处理线程中断，threadId:{}", Thread.currentThread());
                        Thread.currentThread().interrupt();
                    } catch (Exception e2) {
                        log.error("重试处理线程异常，threadId:{}", Thread.currentThread());
                    }
                }
            }
        }
        map.put(RETRY_COUNT_KEY, retryCount);
        map.put(RTN_OBJ_KEY, rtnObj);
        map.put(HANDLER_STATUS_KEY, handlerStatus);
        map.put(ERROR_LOG_KEY, errorLog.toString());
        return map;
    }

//      //多线程处理
//    public Object submit(ExecutorService executorService) {
//        if (executorService == null) {
//            throw new IllegalArgumentException("please choose executorService!");
//        }
//        return executorService.submit((Callable) () -> execute());
//    }

}
