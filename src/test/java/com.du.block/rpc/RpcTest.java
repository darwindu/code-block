package com.du.block.rpc;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.du.block.rpc.impl.ServiceProviderImpl;
import com.du.block.rpc.impl.RPCClient;
import com.du.block.rpc.impl.ServiceCenterImpl;

/**
 * RPC，全称为Remote Procedure Call，即远程过程调用，它是一个计算机通信协议。它允许像调用本地服务一样调用远程服务。它可以有不同的实现方式。如RMI(远程方法调用)、Hessian、Http invoker等。另外，RPC是与语言无关的。
 * RPC本质为消息处理模型，RPC屏蔽了底层不同主机间的通信细节，让进程调用远程的服务就像是本地的服务一样。
 * RPC架构分为三部分：
 * 1）服务提供者，运行在服务器端，提供服务接口定义与服务实现类。
 * 2）服务中心，运行在服务器端，负责将本地服务发布成远程服务，管理远程服务，提供给服务消费者使用。
 * 3）服务消费者，运行在客户端，通过远程代理对象调用远程服务。
 *
 *
 * 以下比较原始的方案实现RPC框架，采用Socket通信、动态代理与反射与Java原生的序列化
 * 缺点：使用Java语言开发，与Java语言高度耦合，并且通信方式采用的Socket是基于BIO实现的，IO效率不高，还有Java原生的序列化机制占内存太多，运行效率也不高。可以考虑从下面几种方法改进。
 * 改进：
 *      可以采用基于JSON数据传输的RPC框架；
 *      可以使用NIO或直接使用Netty替代BIO实现；
 *      使用开源的序列化机制，如Hadoop Avro与Google protobuf等；
 *      服务注册可以使用Zookeeper进行管理，能够让应用更加稳定。
 * @author darwindu
 * @date 2019/3/29
 **/
public class RpcTest {

    public static void main(String[] args) {

        new Thread(new Runnable() {
            public void run() {
                IServiceCenter serviceServer = new ServiceCenterImpl(8088);
                serviceServer.register(IServiceProvider.class, ServiceProviderImpl.class);
                serviceServer.start();
            }
        }).start();

        IServiceProvider service = RPCClient
            .getRemoteProxyObj(IServiceProvider.class, new InetSocketAddress("localhost", 8088));
        System.out.println(service.say("test"));
    }
}