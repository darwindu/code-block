package com.du.block.config;

import java.util.Properties;

/**
 * 配置文件属性对象
 * @author darwindu
 * @date 2019/3/11
 **/
public class ConfigUtil {

    private static Properties appProperties;

    public static void setAppProperties(Properties appProperties) {
        ConfigUtil.appProperties = appProperties;
    }

    public static String getConf(String key) {
        return appProperties.getProperty(key, (String) null);
    }
}
