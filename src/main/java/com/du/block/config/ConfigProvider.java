package com.du.block.config;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 实现读取propertites配置文件
 * @author darwindu
 * @date 2019/3/11
 **/
public class ConfigProvider extends PropertyPlaceholderConfigurer {

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {

        super.processProperties(beanFactoryToProcess, props);
        ConfigUtil.setAppProperties(props);
    }
}
