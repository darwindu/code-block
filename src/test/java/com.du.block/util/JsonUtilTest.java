package com.du.block.util;

import java.util.HashMap;
import java.util.LinkedHashMap;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * jackson测试
 * @author darwindu
 * @date 2019/3/27
 **/
@Slf4j
public class JsonUtilTest {

    @Test
    public void testObjToJsonStr() {

        HashMap<String, Object> propertitesMap = new HashMap<String, Object>();
        propertitesMap.put("id", 1);
        propertitesMap.put("name", "this is name");

        Detail detail = new Detail();
        detail.setCardId("360");
        detail.setCarType("身份证");
        propertitesMap.put("detail", detail);

        String propertites = JsonUtil.objToJsonStr(propertitesMap);
        log.info("[testObjToJsonStr] propertites:{}", propertites);
        Assert.assertNotNull(propertites);
    }

    @Test
    public void testJsonStrToObj() {
        
        String s = "{\"name\":\"zhang san\", \"age\":21}";
        HashMap<String, Object> propertitesMap =
            (HashMap<String, Object>)JsonUtil.jsonStrToObj(HashMap.class, s);

        log.info("[testJsonStrToObj] propertites:{}", propertitesMap);
        Assert.assertNotNull(propertitesMap);
    }

    @Data
    class Detail {
        String cardId;
        String carType;
    }
}
