package com.du.block.rpc.impl;

import com.du.block.rpc.IServiceProvider;

/**
 * 服务提供者接口实现
 * @author darwindu
 * @date 2019/3/29
 **/
public class ServiceProviderImpl implements IServiceProvider {

    @Override
    public String say(String name) {

        return new StringBuffer("hi,").append(name).toString();
    }
}
