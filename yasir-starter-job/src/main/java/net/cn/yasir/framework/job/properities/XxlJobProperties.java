package net.cn.yasir.framework.job.properities;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 属性配置
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/4/29
 */
@ConfigurationProperties(prefix = "yasir.job")
public class XxlJobProperties {

    private boolean enable = false;

    private String adminAddresses;

    private String appName;

    private String ip;

    private int port;

    private String accessToken;

    private String logPath = "logs/yasir-job";

    private int logRetentionDays = 30;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getAdminAddresses() {
        return adminAddresses;
    }

    public void setAdminAddresses(String adminAddresses) {
        this.adminAddresses = adminAddresses;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public int getLogRetentionDays() {
        return logRetentionDays;
    }

    public void setLogRetentionDays(int logRetentionDays) {
        this.logRetentionDays = logRetentionDays;
    }
}
