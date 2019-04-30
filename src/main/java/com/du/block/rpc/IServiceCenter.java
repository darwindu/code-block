package com.du.block.rpc;

import java.io.IOException;

/**
 * 服务中心接口
 * @author darwindu
 * @date 2019/3/29
 **/
public interface IServiceCenter {

    void stop();

    void start();

    void register(Class serviceInterface, Class impl);

    boolean isRunning();

    int getPort();
}
