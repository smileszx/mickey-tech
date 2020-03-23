package com.mickey.tech.test;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author suzhengxiaoq
 * @date 2020/1/18 22:27
 **/
@Slf4j
public class HuToolTest {

    @Test
    public void testMd5() {
        String md5 = SecureUtil.md5("Hello World");
        log.info("{}->{}", "Hello World", md5);
    }

    @Test
    public void testJson () {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"name\":\"zitong\", \"age\":\"26\"}";
        Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
        try{
            map = mapper.readValue(json, new TypeReference<HashMap<String, Map<String, String>>>(){});
            System.out.println(map);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
