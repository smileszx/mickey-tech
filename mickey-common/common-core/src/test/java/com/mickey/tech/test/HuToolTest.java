package com.mickey.tech.test;

import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author suzhengxiao
 * @date 2020/1/18 22:27
 **/
@Slf4j
public class HuToolTest {
    public static void main(String[] args) {
        String md5 = SecureUtil.md5("Hello World");
        log.info("{}->{}", "Hello World", md5);
    }
}
