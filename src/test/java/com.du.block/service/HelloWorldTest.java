package com.du.block.service;

import com.du.block.BaseTest;
import com.du.block.constant.ErrorCode;
import com.du.block.constant.PropertitesConstant;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author darwindu
 * @date 2019/3/27
 **/
@Slf4j
public class HelloWorldTest extends BaseTest {

    @Test
    public void helloWorld() {

        log.info("[helloWorld] class name:{}, method name:{}", testName.getClass().getName(), testName.getMethodName());
        log.info("[helloWorld] timeout:", timeout);

        Assert.assertEquals(PropertitesConstant.WEEVENT_SERVER_PORT, "20191");
        Assert.assertEquals(ErrorCode.SUCCESS.getCode(), 0);
        Assert.assertNotNull("hello world");
    }
}
