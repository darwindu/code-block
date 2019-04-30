package com.du.block.rpc.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.du.block.exception.SdkException;
import com.du.block.rpc.IServiceCenter;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务中心接口实现
 * @author darwindu
 * @date 2019/3/29
 **/
@Slf4j
public class ServiceCenterImpl implements IServiceCenter {

    //阿里要求【强制】线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。
    private static ExecutorService executor = Executors
        .newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private static final HashMap<String, Class> serviceRegistry = new HashMap<String, Class>();

    private static boolean isRunning = false;

    private static int port;

    public ServiceCenterImpl(int port) {
        this.port = port;
    }

    public void stop() {
        isRunning = false;
        executor.shutdown();
    }

    public void start(){

        ServerSocket server = null;
        try {
            server = new ServerSocket();
            server.bind(new InetSocketAddress(port));
            log.info("[start] start server...");
            while (true) {
                // 1.监听客户端的TCP连接，接到TCP连接后将其封装成task，由线程池执行
                executor.execute(new ServiceTask(server.accept()));
            }
        } catch (IOException e) {
            throw new SdkException(e);
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                throw new SdkException(e);
            }
        }
    }

    public void register(Class serviceInterface, Class impl) {
        serviceRegistry.put(serviceInterface.getName(), impl);
    }

    public boolean isRunning() {
        return isRunning;
    }

    public int getPort() {
        return port;
    }

    private static class ServiceTask implements Runnable {

        Socket clent = null;

        public ServiceTask(Socket client) {
            this.clent = client;
        }

        public void run() {
            ObjectInputStream input = null;
            ObjectOutputStream output = null;
            try {
                // 2.将客户端发送的码流反序列化成对象，反射调用服务实现者，获取执行结果
                input = new ObjectInputStream(clent.getInputStream());
                String serviceName = input.readUTF();
                String methodName = input.readUTF();
                Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                Object[] arguments = (Object[]) input.readObject();
                Class serviceClass = serviceRegistry.get(serviceName);
                if (serviceClass == null) {
                    throw new ClassNotFoundException(serviceName + " not found");
                }
                Method method = serviceClass.getMethod(methodName, parameterTypes);
                Object result = method.invoke(serviceClass.newInstance(), arguments);

                // 3.将执行结果反序列化，通过socket发送给客户端
                output = new ObjectOutputStream(clent.getOutputStream());
                output.writeObject(result);
            } catch (Exception e) {
                throw new SdkException(e);
            } finally {
                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        throw new SdkException(e);
                    }
                }
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        throw new SdkException(e);
                    }
                }
                if (clent != null) {
                    try {
                        clent.close();
                    } catch (IOException e) {
                        throw new SdkException(e);
                    }
                }
            }

        }
    }
}
